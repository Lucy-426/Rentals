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
    private final Map<String, ArrayList> filtered_properties = new HashMap<>();
    private PropertyFactory propertyFactory;

public InMemoryDataAccessObject(String csvPath) throws IOException {
//    this.propertyFactory = propertyFactory;

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

//    I don't think we need this if statement?
//    if (csvFile.length() == 0) {
//        save();
//    } else {

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();

            // TODO clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("id, city, address, numRooms, priceRange,numBaths, walkScore, furnished, listingType");

            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String id = String.valueOf(col[headers.get("id")]);
                String city = String.valueOf(col[headers.get("city")]);
                String address = String.valueOf(col[headers.get("address")]);
                String numRooms = String.valueOf(col[headers.get("numRooms")]);
                String priceRange = String.valueOf(col[headers.get("priceRange")]);
                String numBaths = String.valueOf(col[headers.get("numBaths")]);
                String walkScore = String.valueOf(col[headers.get("walkScore")]);
                String furnished = String.valueOf(col[headers.get("furnished")]);
                String listingType = String.valueOf(col[headers.get("listingType")]);

                ArrayList<String> characteristics = new ArrayList<String>();
                characteristics.add(address);
                characteristics.add(city);
                characteristics.add(numRooms);
                characteristics.add(priceRange);
                characteristics.add(numBaths);
                characteristics.add(walkScore);
                characteristics.add(furnished);
                characteristics.add(listingType);

                property_chars.put(id, characteristics);
//            }
        }
    }
}

// this save method will be implemented when we combine this code with the code in PropertyDataAccessObject file.
    @Override
    public void save(Property property) {

    }

    public void filter(HashMap property_chars) {
        filtered_properties.putAll(property_chars);
// my pseudocode:
//        filtered_properties.forEach((id, characteristics) -> {
//          if (city != null or city != "all" or city != characteristics[headers.get("city") or 1])
//               filtered_properties.remove(filtered_properties.remove(id))
//          if (address != null or address != "all" or address != characteristics[0])
//               filtered_properties.remove(filtered_properties.remove(id))
//          if (numRooms != null or numRooms != "all" or numRooms != property.numRooms)
//               filtered_properties.remove(filtered_properties.remove(id))
//        and so on...  });
    }

}
