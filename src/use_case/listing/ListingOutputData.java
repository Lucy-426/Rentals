package use_case.listing;

public class ListingOutputData {

    private final String id;
    private final String address;

    private final String numRooms;

    private final String price;

    private final String numBaths;

    private final String walkScore;

    private final String furnished;

    private final String listingType;

    public ListingOutputData(String id, String address, String numRooms, String price,
                             String numBaths, String walkScore, String furnished, String listingType) {
        this.id = id;
        this.address = address;
        this.numRooms = numRooms;
        this.price = price;
        this.numBaths = numBaths;
        this.walkScore = walkScore;
        this.furnished = furnished;
        this.listingType = listingType;
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

}
