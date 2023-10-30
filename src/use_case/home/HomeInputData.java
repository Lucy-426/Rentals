package use_case.home;

public class HomeInputData {
    final private String address;

    final private String rentalType;

    final private String contact;

    public HomeInputData(String address, String rentalType, String contact) {
        this.address = address;
        this.rentalType = rentalType;
        this.contact = contact;
    }
}
