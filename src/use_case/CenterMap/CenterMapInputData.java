package use_case.search;

public class SearchInputData {

    // Could be lat/lng coordinates, an address, or a city
    final private String address;

    public SearchInputData(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }
}
