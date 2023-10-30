package use_case.home;

public class HomeOutputData {
    private final String address;

    private final String rentalType;

    private final String contact;

    public HomeOutputData(String address, String rentalType, String contact) {
        this.address = address;
        this.rentalType = rentalType;
        this.contact = contact;
    }
}
