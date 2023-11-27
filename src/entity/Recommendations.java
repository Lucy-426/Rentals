package entity;

import data_access.PropertyDataAccessObject;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Recommendations {

    String city;

//    PropertyDataAccessObject properties;
    public Recommendations(String city){
        this.city = city;
    }
    // if filters use listings from filter
    // if no filter pull from cities
//    public Map<String, Property> recommendedListings(){
//        Map<String, Property> recommendedListings = new HashMap<>();
//
//        // maximum three recommendations
//        int count = 0;
//        // if there were no filters inputted, then the recommended listings will come from the same city
//        // of the current listing being viewed
//        if (filtered_properties.isEmpty()){
//            Map<String, Property> cityRecommendations = new HashMap<>();
//
//            // put all listings with the same city into the city recommendations
//            for (Property property : properties.values()){
//                String city = property.getCity();
//                for (Map.Entry<String, Property> entry : cityRecommendations.entrySet()) {
//                    if (city.equals(entry.getValue().getCity())) {
//                        cityRecommendations.put(entry.getKey(), entry.getValue());
//                    }
//                }
//            }
//            // pass through the first 3 recommended listings
//            for (Map.Entry<String, Property> entry : cityRecommendations.entrySet()) {
//                if (count < 3) {
//                    recommendedListings.put(entry.getKey(), entry.getValue());
//                    count += 1;
//                } else{
//                    break;
//                }
//            }
//
//        // else the recommended listings will come from the previous filters
//        } else {
//            for (Map.Entry<String, Property> entry : filtered_properties.entrySet()) {
//                if (count < 3) {
//                    recommendedListings.put(entry.getKey(), entry.getValue());
//                    count += 1;
//                } else{
//                    break;
//                }
//            }
//        }
//
//        return recommendedListings;
//    }

}
