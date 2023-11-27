package interface_adapter.listing;

public class ListingState {

    private String address;

    private String numRooms;

    private String price;

    private String numBaths;

    private String walkScore;

    private String furnished;

    private String listingType;


    public ListingState(ListingState copy) {
        this.address = copy.address;
        this.numRooms = copy.numRooms;
        this.price = copy.price;
        this.numBaths = copy.numBaths;
        this.walkScore = copy.walkScore;
        this.furnished = copy.furnished;
        this.listingType = copy.listingType;

    }

    public ListingState() {}

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
}
