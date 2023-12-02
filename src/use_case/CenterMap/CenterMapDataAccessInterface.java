package use_case.CenterMap;

public interface CenterMapDataAccessInterface {

    // Verifies whether an address exists (users can presumably enter lat/long, a human-readable address, or a city)
    boolean exists(String address);

    // After verifying the address exists, get the latitude and longitude of the address
    double getLat(String address);

    double getLong(String address);
}
