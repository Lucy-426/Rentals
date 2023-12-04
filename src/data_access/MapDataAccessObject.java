package data_access;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import use_case.CenterMap.CenterMapDataAccessInterface;

public class MapDataAccessObject implements CenterMapDataAccessInterface {

    private static final String MAPS_API_KEY = "AIzaSyAMz9doGhcdEYjPoXY3Cv4TCd58-eHDubU";

    @Override
    public boolean exists (String address) {
        /*
        NOTE: The Google Maps API says I don't have permission to validate addresses, so
        I'll address this function again later after completion of other important aspects
        of the project. For now, if you type an invalid address, you will be redirected
        to a random point on a map and an error will show in the terminal.
         */

        /*GeoApiContext context = new GeoApiContext.Builder().apiKey(MAPS_API_KEY).build();

        try {
            // Perform geocoding to get the location information
            GeocodingResult[] results = GeocodingApi.geocode(context, address).await();

            // Check if there is at least one result and that it contains lat/lng coordinates
            return results[0].geometry.location != null;

        } catch (Exception e) {
            // Not a valid address
            e.printStackTrace();
            return false;
        }*/
        return true;
    }

    @Override
    public double getLat(String address) {
        try {
            PlacesSearchResponse placesSearchResponse = PlacesApi.textSearchQuery(
                    new GeoApiContext.Builder().apiKey(MAPS_API_KEY).build(),
                    address).await();

            PlacesSearchResult result = placesSearchResponse.results[0];
            LatLng location = result.geometry.location;
            return location.lat;

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
            return location.lng;

        } catch(Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
