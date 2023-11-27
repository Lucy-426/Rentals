package entity;

import java.sql.ClientInfoStatus;

public class PropertyFactory {
    public Property create(String id, String city, String address, String numRooms, String priceRange,
                           String numBaths, String walkScore, String furnished, String listingType) {
        return new Property(id, city, address, numRooms, priceRange, numBaths, walkScore, furnished, listingType);
    }
}
