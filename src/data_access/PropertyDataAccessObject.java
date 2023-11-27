package data_access;

import com.jayway.jsonpath.JsonPath;
import entity.Property;
import entity.PropertyFactory;
import kotlin.Pair;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.PlacesApi;
import com.google.maps.model.*;

import use_case.search.SearchDataAccessInterface;

import java.io.*;
import java.util.*;

public class PropertyDataAccessObject implements HomeSearchDataAccessInterface, SearchDataAccessInterface {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Property> properties = new HashMap<>();

    // Root URL - can later adapt so that for various functions, we attach ending (i.e. .../property/detail)
    private static final String API_URL = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/";
    private static final String API_TOKEN = "a29ec8d3d48c6a36e4ce59f96a94606a";
    private static final String MAPS_API_KEY = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

    // The cities that we are getting listings from
    private static final Pair<String, String> SF_CA = new Pair<>("37.656305","-122.417006");
    private static final Pair<String, String> MINNEAPOLIS_MN = new Pair<>("44.996091", "-93.364628");
    private static final Pair<String, String> BOSTON_MA = new Pair<>("42.348866", "-71.064589");
    private static final Pair<String, String> LA_CA = new Pair<>("34.029082", "-118.25947");
    private static final Pair<String, String> CHICAGO_IL = new Pair<>("41.825105", "-87.663623");
    private static final Pair<String, String> MIAMI_FL = new Pair<>("25.765623", "-80.505745");
    private static final Pair<String, String> PHILADELPHIA_PA = new Pair<>("39.993614", "-75.150923");
    private static final Pair<String, String> DETROIT_MI = new Pair<>("42.352656", "-83.088938");
    private static final Pair<String, String> ATLANTA_GA = new Pair<>("33.785802", "-84.41739");
    private static final Pair<String, String> BUFFALO_NY = new Pair<>("42.926907", "-78.815458");

    // a list that will contain all the cities above
    private static final ArrayList<Pair> cities = new ArrayList<>();

    private PropertyFactory propertyFactory;

    public PropertyDataAccessObject(String csvPath, PropertyFactory propertyFactory) throws IOException {
        this.propertyFactory = propertyFactory;

        // saving all the cities from above to a list
        cities.add(SF_CA);
        cities.add(MINNEAPOLIS_MN);
        cities.add(BOSTON_MA);
        cities.add(LA_CA);
        cities.add(CHICAGO_IL);
        cities.add(MIAMI_FL);
        cities.add(PHILADELPHIA_PA);
        cities.add(DETROIT_MI);
        cities.add(ATLANTA_GA);
        cities.add(BUFFALO_NY);

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
        if(csvFile.length() == 0) {
            for (Pair city: cities) {
                load(city);
            }

            // Once all the cities' data have been loaded,
            // save (write all the data) to csv
            this.save();
        }

    }

    public void save(Property property) {

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

                    String walkScore = "0"; //TODO: This is to be filled in based on calculations that Janna is working on

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
    public boolean exists(String address) {
        GeoApiContext context = new GeoApiContext.Builder().apiKey(MAPS_API_KEY).build();

        try {
            // Perform geocoding to get the location information
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

            // Check if there is at least one result and that it contains lat/lng coordinates
            return results[0].geometry.location != null;

        } catch (Exception e) {
            // Not a valid address
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public double getLat(String address) {
        try {
            PlacesSearchResponse placesSearchResponse = PlacesApi.textSearchQuery(
                    new GeoApiContext.Builder().apiKey(MAPS_API_KEY).build(),
                    address).await();

            PlacesSearchResult result = placesSearchResponse.results[0];
            LatLng location = result.geometry.location;
            double latitude = location.lat;
            return latitude;

        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public double getLong(String address) {
        try {
            PlacesSearchResponse placesSearchResponse = PlacesApi.textSearchQuery(
                    new GeoApiContext.Builder().apiKey(MAPS_API_KEY).build(),
                    address).await();

            PlacesSearchResult result = placesSearchResponse.results[0];
            LatLng location = result.geometry.location;
            double longitude = location.lng;
            return longitude;

        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void centerMap(double lat, double lng) {

    }
}
