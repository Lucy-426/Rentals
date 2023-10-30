package entity;

public class PropertyFactory {
    public Property create(String address, String rentalType, String contact) {
        return new Property(address, rentalType, contact);
    }
}
