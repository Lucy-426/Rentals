package entity;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import entity.DistanceCalculation;

public class WalkScore {
    public static double assignScore(double distance) {
        // 5-minute walk to destination
        if (distance >= 0 && distance < 0.4) {
            return 1;
        }
        // 10-minute walk to destination
        else if (distance >= 0.4 && distance < 0.8) {
            return 0.8;
        }
        // 15-minute walk to destination
        else if (distance >= 0.8 && distance < 1.2) {
            return 0.6;
        }
        // 20-minute walk to destination
        else if (distance >= 1.2 && distance < 1.6) {
            return 0.4;
        }
        // 25-minute walk to destination
        else if (distance >= 1.6 && distance < 2) {
            return 0.2;
        }
        // More than a 25-minute walk to destination
        else {
            return 0;
        }
    }

    public static int calculation(double startLat, double startLng) {
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

                for (PlacesSearchResult result : response.results) {
                    // Uncomment this code to see which places are being generated
                    System.out.println("Place Name: " + result.name);

                    // Get the latitude and longitude of generated place
                    double endLat = result.geometry.location.lat;
                    double endLng = result.geometry.location.lng;

                    // Calculate the distance between the origin and the generated place
                    double distance = DistanceCalculation.calculation(startLat, startLng, endLat, endLng);

                    // Uncomment this code to see the distance between the origin and the generated place
                    System.out.println("Distance from origin: " + distance);

                    // Get each place's individual walk score (worth 1% of total walk score)
                    double score = assignScore(distance);

                    // Uncomment this code to see the individual walk score of each generated place
                    System.out.println("Walk score: " + score);

                    // Accumulate total walk score with individual walk score
                    walkScore += score;
                }
            }
            // Print out walk score for testing purposes
            System.out.println((int) walkScore);
            return (int) walkScore;
        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Sample calls to the walk score function - delete at the end
    // Note that this function takes a bit of time to return a value; but you can verify that it works
    // by enabling the print statements to see that it is iterating through each place and
    // accumulating the walk score.
    public static void main(String[] args) {
//        calculation(43.6598084,-79.3998729); // A location in Toronto near campus
//        calculation(43.5422741,-79.694035); // A location in Mississauga near UTM
        calculation(43.649093,-79.394538); // A location in Toronto near the lakefront
    }
}