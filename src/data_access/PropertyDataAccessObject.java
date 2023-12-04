package data_access;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.jayway.jsonpath.JsonPath;
import entity.Property;
import entity.PropertyFactory;
import kotlin.Pair;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.home.HomeSearchDataAccessInterface;

import java.io.*;
import java.util.*;

public class PropertyDataAccessObject implements HomeSearchDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Property> properties = new HashMap<>();

    private Map<String, Property> filtered_properties;

    private Map<String, Property> filteredProperties;

    private Map<String, Property> recommendedProperties;

    private final Map<String, Pair> coordinates = new HashMap<>();

    private final WalkScoreDataAccessObject walkScoreCalculator = new WalkScoreDataAccessObject();

    // Root URL - can later adapt so that for various functions, we attach ending (i.e. .../property/detail)
    private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/";
    private static final String API_TOKEN = "bdc142f975386786593145e4c20e19e3";

    // The cities that we are getting listings from
//    private static final Pair<String, String> SF_CA = new Pair<>("37.656305", "-122.417006");
//    private static final Pair<String, String> MINNEAPOLIS_MN = new Pair<>("44.996091", "-93.364628");
    private static final Pair<String, String> LA_CA = new Pair<>("34.029082", "-118.25947");
//    private static final Pair<String, String> PHILADELPHIA_PA = new Pair<>("39.993614", "-75.150923");
//    private static final Pair<String, String> DETROIT_MI = new Pair<>("42.352656", "-83.088938");
//    private static final Pair<String, String> ATLANTA_GA = new Pair<>("33.785802", "-84.41739");
//    private static final Pair<String, String> BUFFALO_NY = new Pair<>("42.926907", "-78.815458");

    // a list that will contain all the cities above
    private static final ArrayList<Pair> cities = new ArrayList<>();

    private PropertyFactory propertyFactory;

    private Property inputProperty;

    private final String MAPS_API_KEY = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

    public PropertyDataAccessObject(String csvPath, PropertyFactory propertyFactory) throws IOException {
        this.propertyFactory = propertyFactory;

        // saving all the cities from above to a list
//        cities.add(SF_CA);
//        cities.add(MINNEAPOLIS_MN);
        cities.add(LA_CA);
//        cities.add(PHILADELPHIA_PA);
//        cities.add(DETROIT_MI);
//        cities.add(ATLANTA_GA);
//        cities.add(BUFFALO_NY);

        csvFile = new File(csvPath);
        headers.put("id", 0);
        headers.put("city", 1);
        headers.put("address", 2);
        headers.put("numRooms", 3);
        headers.put("priceRange", 4);
        headers.put("numBaths", 5);
        headers.put("walkScore", 6);
        headers.put("furnished", 7);
        headers.put("listingType", 8);

        // go through each city and load in the data for the listings in that city
        if (csvFile.length() == 0) {
            for (Pair city : cities) {
                load(city);
            }

            // Once all the cities' data have been loaded,
            // save (write all the data) to csv
            this.save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("id,city,address,numRooms,priceRange,numBaths,walkScore,furnished,listingType");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String id = String.valueOf(col[headers.get("id")]);
                    String city = String.valueOf(col[headers.get("city")]);
                    String address = String.valueOf(col[headers.get("address")]);
                    String rooms = String.valueOf(col[headers.get("numRooms")]);
                    String price = String.valueOf(col[headers.get("priceRange")]);
                    String baths = String.valueOf(col[headers.get("numBaths")]);
                    String walkscore = String.valueOf(col[headers.get("walkScore")]);
                    String furnished = String.valueOf(col[headers.get("furnished")]);
                    String listingType = String.valueOf(col[headers.get("listingType")]);
                    Property property = propertyFactory.create(id, city, address, rooms, price, baths, walkscore, furnished, listingType);
                    properties.put(property.getID(), property);
                }
            }
        }

    }

    public void save(Property property) {
        this.inputProperty = property;
    }

    // Method to call the API for a given city and parse the json response
    private void load(Pair<String, String> city) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_URL + "assessment/snapshot?latitude=" + city.getFirst() + "&longitude=" + city.getSecond() + "&radius=1&orderby=distance+desc")
                .get()
                .addHeader("apikey", API_TOKEN)
                .build();

        try {
            Response response = client.newCall(request).execute();

            JSONObject responseBody = new JSONObject(response.body().string());

            // for each property in the listing, filter out the fields that we need and create the property
            JSONArray rawPropertiesData = responseBody.getJSONArray("property");
            for (int i = 0; i < rawPropertiesData.length(); i++) {
                String propertyJson = rawPropertiesData.getJSONObject(i).toString();

                // Check if property is residential, don't use the ones that are not
                String propertyType = JsonPath.read(propertyJson, "$.summary[?(@.propsubtype == 'Residential')]").toString();
                if (!Objects.equals(propertyType, "[]")) {

                    // get the attributes we need for each property
                    String id_before = JsonPath.read(propertyJson, "$.identifier[?(@.Id != '')].Id").toString();
                    String id = id_before.substring(1, id_before.length() - 1);

                    String city_located = JsonPath.read(propertyJson, "$.address.locality");

                    String address = JsonPath.read(propertyJson, "$.address.line1");

                    String numRooms_before = JsonPath.read(propertyJson, "$.building.rooms[?(@.beds != '')].beds").toString();
                    String numRooms = numRooms_before.substring(1, numRooms_before.length() - 1);

                    String priceRange_before = JsonPath.read(propertyJson, "$.assessment.assessed.[?(@.assdttlvalue != '')].assdttlvalue").toString();
                    String priceRange = priceRange_before.substring(1, priceRange_before.length() - 1);

                    String numBaths_before = JsonPath.read(propertyJson, "$.building.rooms[?(@.bathstotal != '')].bathstotal").toString();
                    String numBaths = numBaths_before.substring(1, numBaths_before.length() - 1);

                    String latitude = JsonPath.read(propertyJson, "$.location.latitude");
                    double lat = Double.parseDouble(latitude);
                    String longitude = JsonPath.read(propertyJson, "$.location.longitude");
                    double lon = Double.parseDouble(longitude);

                    coordinates.put(id, new Pair(lat, lon));

                    // TODO: Still need to fix this because it takes too long to run so no memory error happens
//                    String walkScore = Integer.toString(walkScoreCalculator.calculation(lat, lon));
                    String walkScore = "0";

                    List<String> givenList = Arrays.asList("Yes", "No");
                    Random rand = new Random();
                    String furnished = givenList.get(rand.nextInt(givenList.size()));

                    String listingType = JsonPath.read(propertyJson, "$.summary.propclass");

                    // Create the property based on the attributes we parsed above
                    Property newProperty = propertyFactory.create(id, city_located, address, numRooms, priceRange, numBaths, walkScore, furnished, listingType);

                    // Save the property to list of properties
                    properties.put(newProperty.getID(), newProperty);
                }
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    //    TODO: test the filter method
    @Override
    public void filter() {
        filteredProperties = new HashMap<>();
        String id = inputProperty.getID();
        String address = inputProperty.getAddress();
        String furnished = inputProperty.getFurnished();


//      goes over the copy of the list of properties made from csv files and
//      removes each id:property if it doesn't match the input property object attributes (user information)
        for (Map.Entry<String, Property> entry : properties.entrySet()) {
            if ((id == null) || id.equals("all") || id.equals(entry.getValue().getID())) {
                if (cityCheck(entry.getValue())) {
                    if (addressCheck(entry.getValue())) {
                        if (numRoomsCheck(entry.getValue())) {
                            if (priceRangeCheck(entry.getValue())) {
                                if (numBathsCheck(entry.getValue())) {
                                    if (walkscoreCheck(entry.getValue())) {
                                        if ((furnished == null) || furnished.equals("all") || furnished.equals(entry.getValue().getFurnished())) {
                                            if (listingTypeCheck(entry.getValue())) {
                                                filteredProperties.put(entry.getKey(), entry.getValue());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    private boolean priceRangeCheck(Property property) {
        String helperPriceRange = inputProperty.getPriceRange();
        String propertyPriceRange = property.getPriceRange();

        if (helperPriceRange == null || helperPriceRange.equals("all") || helperPriceRange.isEmpty()) {
            return true;
        } else if (helperPriceRange.equals("<100000")) {
            return propertyPriceRange != null && !propertyPriceRange.trim().isEmpty() && Integer.valueOf(propertyPriceRange.trim()) <= 100000;
        } else if (helperPriceRange.equals("100000-300000")) {
            return propertyPriceRange != null && !propertyPriceRange.trim().isEmpty() && 100000 <= Integer.valueOf(propertyPriceRange.trim()) && Integer.valueOf(propertyPriceRange.trim()) <= 300000;
        } else if (helperPriceRange.equals("300000-500000")) {
            return propertyPriceRange != null && !propertyPriceRange.trim().isEmpty() && 300000 <= Integer.valueOf(propertyPriceRange.trim()) && Integer.valueOf(propertyPriceRange.trim()) <= 500000;
        } else {
            return propertyPriceRange != null && !propertyPriceRange.trim().isEmpty() && 500000 <= Integer.valueOf(propertyPriceRange.trim());
        }
    }


    //    helper method to check if input property listing type filter word is found in csv property listing type
    private boolean listingTypeCheck(Property property) {
        String listingType = inputProperty.getListingType();
        if (listingType == null || listingType.equals("all")) {
            return true;
        } else if (listingType.equals("other")) {
//            return true if "House", "Townhouse", "Apartment" not in the csv property
            return !property.getListingType().contains("Residence") && !property.getListingType().contains("Townhouse") && !property.getListingType().contains("Apartment");
        } else if (listingType.equals("House")) {
            return (property.getListingType().contains("Residence"));
        } else {
            return (property.getListingType().contains(listingType));
        }
    }

    private boolean numRoomsCheck(Property property) {
        String helperNumRooms = inputProperty.getNumRooms();
        String propertyNumRooms = property.getNumRooms();

        if (helperNumRooms == null || helperNumRooms.equals("all") || helperNumRooms.isEmpty()) {
            return true;
        } else if (helperNumRooms.equals("5+")) {
            return propertyNumRooms != null && !propertyNumRooms.trim().isEmpty() && Integer.valueOf(propertyNumRooms.trim()) >= 5;
        } else {
            return propertyNumRooms != null && !propertyNumRooms.trim().isEmpty() && Integer.valueOf(helperNumRooms).equals(Integer.valueOf(propertyNumRooms));
        }
    }

    private boolean numBathsCheck(Property property) {
        String helperNumBaths = inputProperty.getNumBaths();
        String propertyNumBaths = property.getNumBaths();

        if (helperNumBaths == null || helperNumBaths.equals("all") || helperNumBaths.isEmpty()) {
            return true;
        } else if (helperNumBaths.equals("4+")) {
            return propertyNumBaths != null && !propertyNumBaths.trim().isEmpty() && Integer.valueOf(propertyNumBaths.trim()) >= 4;
        } else {
            return propertyNumBaths != null && !propertyNumBaths.trim().isEmpty() && Integer.valueOf(helperNumBaths).equals(Integer.valueOf(propertyNumBaths));
        }
    }

    private boolean walkscoreCheck(Property property) {
        String helperWalkscore = inputProperty.getWalkScore();
        if (helperWalkscore == null || helperWalkscore.equals("all")) {
            return true;
        } else if (helperWalkscore.equals("10-30")) {
            return Integer.valueOf(property.getWalkScore()) <= 30 && Integer.valueOf(property.getWalkScore()) >= 10;
        } else if (helperWalkscore.equals("30-60")) {
            return Integer.valueOf(property.getWalkScore()) >= 30 && Integer.valueOf(property.getWalkScore()) <= 60;
        } else {
//            don't need < 10 because properties always have walkscore less than 10
            return Integer.valueOf(property.getWalkScore()) >= 60;
        }
    }

    private boolean cityCheck(Property property) {
        String helperCity = inputProperty.getCity();
        if (helperCity == null) {
            return true;
        } else {
            return (property.getCity().contains(helperCity.toUpperCase()));
        }
    }

    //    after inputting a city then deleting it with other filters the same, after pressing search button
//    it recognizes it as an empty address and makes address attribute be empty string.
//    This makes the list of properties empty
    private boolean addressCheck(Property property) {
        String helperAddress = inputProperty.getAddress();
        if (helperAddress == null || helperAddress.isEmpty()) {
            return true;
        } else {
            return (property.getAddress().contains(helperAddress.toUpperCase()));
        }
    }


    // Writing the Property object inside of properties to the csv file
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            // Go through properties and format attributes to csv file columns
            for (Property property : properties.values()) {
                String line = "%s,%s,%s,%s,%s,%s,%s,%s,%s".formatted(
                        property.getID(), property.getCity(), property.getAddress(), property.getNumRooms(), property.getPriceRange(),
                        property.getNumBaths(), property.getWalkScore(), property.getFurnished(), property.getListingType()
                );
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public HashMap<String, String> getFilteredProperties() {
        HashMap<String, String> displayProperties = new HashMap<>();
        for (HashMap.Entry<String, Property> entry : filteredProperties.entrySet()) {
            displayProperties.put(entry.getKey(), entry.getValue().getAddress());
        }
        return displayProperties;
    }

    @Override
    public Property getProperty(String id) {
        return properties.get(id);
    }

    @Override
    public Set<Waypoint> getCoordinates(HashMap<Waypoint, String> properties) {
        return properties.keySet();
    }

    @Override
    public HashMap<Waypoint, String> getWaypointToID(HashMap<String, String> properties) {
        System.out.println(properties);
        HashMap<Waypoint, String> waypointToID = new HashMap<>();
        for (String id : properties.keySet()) {
            System.out.println(id);
            double latitude = (double) coordinates.get(id).getFirst();
            System.out.println(latitude);
            double longitude = (double) coordinates.get(id).getSecond();
            System.out.println(longitude);
            Waypoint waypoint = new DefaultWaypoint(latitude, longitude);
            System.out.println(waypoint);
            waypointToID.put(waypoint, id);
        }
        System.out.println(waypointToID);
        return waypointToID;
    }

    @Override
    public double getLat(String id) {
        try {
            if (coordinates.containsKey(id)) {
                return (double) coordinates.get(id).getFirst();
            } else {
                System.out.println(id + " does not exist");
                System.out.println(coordinates.get(id).getFirst());
                return 0;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public double getLong(String id) {
        try {
            if (coordinates.containsKey(id)) {
                return (double) coordinates.get(id).getSecond();
            } else {
                System.out.println(id + " does not exist");
                return 0;
            }
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    @Override
    public void makeRecommendations(Property property) {
        recommendedProperties = new HashMap<>();
        String city = property.getCity();

        // make a hashmap and add properties with the same city to it
        HashMap<String, Property> cityRecommendations = new HashMap<>();
        for (Map.Entry<String, Property> entry : filteredProperties.entrySet()) {
            if (city.equals(entry.getValue().getCity()) && !entry.getValue().getID().equals(property.getID())) {
                cityRecommendations.put(entry.getKey(), entry.getValue());
            }
        }
        if (cityRecommendations.size() < 3) {
            for (Map.Entry<String, Property> entry : properties.entrySet()) {
                if ((city.equals(entry.getValue().getCity()) && !entry.getValue().getID().equals(property.getID()))) {
                    cityRecommendations.put(entry.getKey(), entry.getValue());
                }
            }
        }
        int count = 0;
        for (Map.Entry<String, Property> entry : cityRecommendations.entrySet()) {
            // pass through the first 3 recommended listings from cityRecommendations
            if (count < 3) {
                recommendedProperties.put(entry.getKey(), entry.getValue());
                count += 1;
            } else {
                break;
            }
        }

    }

    @Override
    public HashMap<String, String> getRecommendedProperties() {
        HashMap<String, String> recommendations = new HashMap<>();
        for (HashMap.Entry<String, Property> entry : recommendedProperties.entrySet()) {
            recommendations.put(entry.getKey(), entry.getValue().getAddress());
        }
        return recommendations;
    }
}
