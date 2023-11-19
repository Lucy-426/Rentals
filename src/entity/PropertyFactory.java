package entity;

import java.sql.ClientInfoStatus;

public class PropertyFactory {
    public Property create(String address, String numRooms, String priceRange,
                           String numBaths, String walkScore, String furnished, String listingType) {
        return new Property(address, numRooms, priceRange, numBaths, walkScore, furnished, listingType);
    }
}
