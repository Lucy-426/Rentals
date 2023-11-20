package entity;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.TravelMode;

import java.util.concurrent.TimeUnit;

public class DistanceCalculation {
    public static long calculation(double startLat, double startLng, double endLat, double endLng) {
        String apiKey = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

        try {
            GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

            // Create a directions request
            DirectionsApiRequest request = DirectionsApi.newRequest(context)
                    .origin(new com.google.maps.model.LatLng(startLat, startLng))
                    .destination(new com.google.maps.model.LatLng(endLat, endLng))
                    .mode(TravelMode.WALKING); // You can change the travel mode if needed

            // Execute the request
            DirectionsResult result = request.await();

            // Get the total distance in meters
            long distanceInMeters = result.routes[0].legs[0].distance.inMeters;

            // Print the result
            return distanceInMeters;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }
}
