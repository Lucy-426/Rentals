package data_access;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import entity.DistanceCalculation;

public class WalkScoreDataAccessObject {
    public static double assignScore(double distance) {
        // 5-minute walk to destination
        if (distance >= 0 && distance < 0.4) {
            return 2;
        }
        // 10-minute walk to destination
        else if (distance >= 0.4 && distance < 0.8) {
            return 1.6;
        }
        // 15-minute walk to destination
        else if (distance >= 0.8 && distance < 1.2) {
            return 1.2;
        }
        // 20-minute walk to destination
        else if (distance >= 1.2 && distance < 1.6) {
            return 0.8;
        }
        // 25-minute walk to destination
        else if (distance >= 1.6 && distance < 2) {
            return 0.4;
        }
        // More than a 25-minute walk to destination
        else {
            return 0;
        }
    }

    public int calculation(double startLat, double startLng) {
        String apiKey = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

        LatLng location = new LatLng(startLat, startLng);

        // Set 1km radius - anything higher than that will generate places that are too far from the origin
        int radius = 1000;

        PlaceType[] placeTypes = {
                PlaceType.TRANSIT_STATION,
                PlaceType.GROCERY_OR_SUPERMARKET,
                PlaceType.DOCTOR,
                PlaceType.STORE,
                PlaceType.LIBRARY
        };

        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
            double walkScore = 0;

            // Generally always generates 20 places per place type, unless your origin is
            // somewhere in the suburbs like Mississauga where most places require a car
            for (PlaceType place: placeTypes) {
                PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, location)
                        .radius(radius)
                        .type(place)
                        .await();

                int counter = 0;
                for (PlacesSearchResult result : response.results) {
                    if (counter < 10 && counter <= response.results.length) {

                        // Get the latitude and longitude of generated place
                        double endLat = result.geometry.location.lat;
                        double endLng = result.geometry.location.lng;

                        // Calculate the distance between the origin and the generated place
                        double distance = DistanceCalculation.calculation(startLat, startLng, endLat, endLng);

                        // Get each place's individual walk score (worth 1% of total walk score)
                        double score = assignScore(distance);

                        // Accumulate total walk score with individual walk score
                        walkScore += score;

                        // Increment counter
                        counter++;
                    } else {
                        break;
                    }
                }
            }
            // Print out walk score for testing purposes
            System.out.println((int) walkScore);
            return (int) walkScore + 50;
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}