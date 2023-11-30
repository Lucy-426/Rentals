package interface_adapter.homeSearch;

public class HomeSearchState {
    // Because of the previous copy constructor, the default constructor must be explicit.

    private String id;

    private String city;

    private String address;

    private String numRooms;

    private String priceRange;

    private String numBaths;

    private String walkScore;

    private String furnished;

    private String listingType;

    private boolean loggedIn = false;

    public HomeSearchState(HomeSearchState copy) {
        id = copy.id;
        city = copy.city;
        address = copy.address;
        numRooms = copy.numRooms;
        priceRange = copy.priceRange;
        numBaths = copy.numBaths;
        walkScore = copy.walkScore;
        furnished = copy.furnished;
        listingType = copy.listingType;
        loggedIn = copy.loggedIn;
    }
    public HomeSearchState() {}
    public String getId() { return id; }

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

    public boolean getLoggedIn() { return loggedIn; }

    public void setId(String id) { this.id = id; }

    public void setCity(String city) { this.city = city; }

    public void setAddress(String address){
        this.address = address;
    }

    public void setNumRooms(String numRooms){
        this.numRooms = numRooms;
    }

    public void setPriceRange(String priceRange) {
        this.priceRange = priceRange;
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

    public void setLoggedIn(boolean loggedIn) { this.loggedIn = loggedIn; }

}
