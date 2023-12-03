package interface_adapter.listing;

import java.util.HashMap;

public class ListingState {

    private String id;

    private String city;
    private String address;

    private String numRooms;

    private String price;

    private String numBaths;

    private String walkScore;

    private String furnished;

    private String listingType;

    private HashMap<String, String> recommendations;

    private boolean loggedIn = false;

    private String username = "";

    private String saveMsg = null;


    public ListingState(ListingState copy) {
        this.id = copy.id;
        this.city = copy.city;
        this.address = copy.address;
        this.numRooms = copy.numRooms;
        this.price = copy.price;
        this.numBaths = copy.numBaths;
        this.walkScore = copy.walkScore;
        this.furnished = copy.furnished;
        this.listingType = copy.listingType;
        this.recommendations = copy.recommendations;
        this.loggedIn = copy.loggedIn;
        this.username = copy.username;
        this.saveMsg = copy.saveMsg;
    }

    public ListingState() {}

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

    public boolean isLoggedIn() { return loggedIn; }

    public String getUsername() { return username; }

    public String getSaveMsg() { return saveMsg; }

    public void setId(String id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setNumRooms(String numRooms){
        this.numRooms = numRooms;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setNumBaths(String numBaths) {
        this.numBaths = numBaths;
    }

    public void setWalkScore(String walkScore) {
        this.walkScore = walkScore;
    }

    public void setFurnished(String furnished) {
        this.furnished = furnished;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public void setRecommendations(HashMap<String, String> recommendations) {
        this.recommendations = recommendations;
    }

    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }

    public void setUsername(String username) { this.username = username; }

    public void setSaveMsg(String msg) { this.saveMsg = msg; }
}
