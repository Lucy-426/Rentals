package entity;
import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

public class DistanceCalculation {
    public static double calculation(double startLat, double startLng, double endLat, double endLng) {
        String apiKey = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

        // Set up the GeoApiContext with API key
        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

        // Specify the origin and destination coordinates
        String origin = startLat + "," + startLng;
        String destination = endLat + "," + endLng;

        try {
            // Make a query to get the walking distance in kilometers
            DirectionsResult result = DirectionsApi.newRequest(context)
                    .origin(origin)
                    .destination(destination)
                    .mode(TravelMode.WALKING)
                    .await();

            // Extract and return the walking distance
            double distance = result.routes[0].legs[0].distance.inMeters;
            return distance/1000;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    // Test from Bahen to Eaton - delete at the end
    public static void main(String[] args) {
        calculation(43.6598084,-79.3998729,43.6544421,-79.3832743);
    }
}
