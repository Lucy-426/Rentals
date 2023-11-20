package entity;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import entity.DistanceCalculation;

import java.sql.SQLOutput;
import java.util.Arrays;

public class WalkScore {
    public static void main(String[] args) {
        String apiKey = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

        LatLng location = new LatLng(43.6669356, -79.3945384);
        int radius = 5000; // 5km radius

        PlaceType[] placeTypes = {
                PlaceType.RESTAURANT, // 5 %
                PlaceType.CONVENIENCE_STORE, // 10 %
                PlaceType.TRANSIT_STATION, // 20 %
                PlaceType.GROCERY_OR_SUPERMARKET, // 20 %
                PlaceType.GYM, // 5 %
                PlaceType.DOCTOR, // 10 %
                PlaceType.UNIVERSITY // 30 %
        };

        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

            // Automatically generates max 20 places per type
            for (PlaceType place: placeTypes) {
                PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                        .radius(radius)
                        .type(place)
                        .await();

                int placeCount = response.results.length;
                System.out.println("Number of nearby places: " + placeCount);
                for (PlacesSearchResult result : response.results) {
                    System.out.println("Place Name: " + result.name);
                    System.out.println("Place Type: " + Arrays.toString(result.types));
                    // System.out.println("Distance from origin: " + );
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}