package entity;

import java.util.HashMap;
import java.util.Map;

public class Property {
    private final String id;

    private final String city;

    private final String address;

    private final String numRooms;

    private final String priceRange;

    private final String numBaths;

    private final String walkScore;

    private final String furnished;

    private final String listingType;

    private final Map<String, Property> recListings;

    public Property(String id, String city, String address, String numRooms, String priceRange,
                    String numBaths, String walkScore, String furnished, String listingType) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.numRooms = numRooms;
        this.priceRange = priceRange;
        this.numBaths = numBaths;
        this.walkScore = walkScore;
        this.furnished = furnished;
        this.listingType = listingType;
        this.recListings = new HashMap<>();
    }

    public String getID() { return id; }
    public String getCity() { return city; }
    public String getAddress(){
        return address;
    }

    public String getNumRooms(){
        return numRooms;
    }

    public String getPriceRange() {
        return priceRange;
    }

    public String getNumBaths() {
        return numBaths;
    }

    public String getWalkScore() {
        return walkScore;
    }

    public String getFurnished() {
        return furnished;
    }

    public String getListingType() {
        return listingType;
    }

    public Map<String, Property> getRecListings() {
        return recListings;
    }

    public void setRecListings(Map<String, Property> fromFilteredListings){
        recListings.putAll(fromFilteredListings);
    }
}
