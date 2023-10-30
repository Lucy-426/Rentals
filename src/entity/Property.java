package entity;

public class Property {

    private final String address;
    private final String rentalType;
    private final String contact;

    public Property(String address, String rentalType, String contact) {
        this.address = address;
        this.rentalType = rentalType;
        this.contact = contact;
    }

    public String getAddress(){
        return address;
    }

    public String getRentalType(){
        return rentalType;
    }

    public String getContact() {
        return contact;
    }
}
