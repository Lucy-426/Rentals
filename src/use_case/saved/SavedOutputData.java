package use_case.saved;

import java.util.HashMap;

public class SavedOutputData {

    private final String id;

    private final String city;
    private final String address;

    private final String numRooms;

    private final String price;

    private final String numBaths;

    private final String walkScore;

    private final String furnished;

    private final String listingType;

    private final HashMap<String, String> recommendations;

    public SavedOutputData(String id, String city, String address, String numRooms, String price,
                           String numBaths, String walkScore, String furnished, String listingType,
                           HashMap<String, String> recommendations) {
        this.id = id;
        this.city = city;
        this.address = address;
        this.numRooms = numRooms;
        this.price = price;
        this.numBaths = numBaths;
        this.walkScore = walkScore;
        this.furnished = furnished;
        this.listingType = listingType;
        this.recommendations = recommendations;
    }

    public String getId() {
        return id;
    }
    public String getCity() {
        return city;
    }
    public String getAddress(){
        return address;
    }
    public String getNumRooms() {
        return numRooms;
    }
    public String getPrice() {
        return price;
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

    public HashMap<String, String> getRecommendations() {
        return recommendations;
    }

}
