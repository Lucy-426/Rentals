package use_case.CenterMap;

public class CenterMapInputData {

    // Could be lat/lng coordinates, an address, or a city
    final private String address;

    public CenterMapInputData(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }
}
