package data_access;

import entity.Property;
import entity.PropertyFactory;
import use_case.home.HomeSearchDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
public class InMemoryDataAccessObject implements HomeSearchDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, ArrayList> property_chars = new HashMap<>();
    private PropertyFactory propertyFactory;

//    might need to throw IO Exception?
public InMemoryDataAccessObject(String csvPath) throws IOException {
//    this.propertyFactory = propertyFactory;

    csvFile = new File(csvPath);
    headers.put("id", 0);
    headers.put("address", 1);
    headers.put("numRooms", 2);
    headers.put("priceRange", 3);
    headers.put("numBaths", 4);
    headers.put("walkScore", 5);
    headers.put("furnished", 6);
    headers.put("listingType", 7);

//    I don't think we need this if statement?
//    if (csvFile.length() == 0) {
//        save();
//    } else {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            // TODO clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("username,password,creation_time");

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String id = String.valueOf(col[headers.get("id")]);
                String address = String.valueOf(col[headers.get("address")]);
                String numRooms = String.valueOf(col[headers.get("numRooms")]);
                String priceRange = String.valueOf(col[headers.get("priceRange")]);
                String numBaths = String.valueOf(col[headers.get("numBaths")]);
                String furnished = String.valueOf(col[headers.get("furnished")]);
                String listingType = String.valueOf(col[headers.get("listingType")]);

                ArrayList<String> characteristics = new ArrayList<String>();
                characteristics.add(address);
                characteristics.add(numRooms);
                characteristics.add(priceRange);
                characteristics.add(numBaths);
                characteristics.add(furnished);
                characteristics.add(listingType);

                property_chars.put(id, characteristics);
//            }
        }
    }
}


    public void save(Property property) {
//        properties.put(property.getAddress(), property);
    }

}
