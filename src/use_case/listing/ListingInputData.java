package use_case.listing;

public class ListingInputData {
    // should we just have a property id all pull all the data from that
//    private final String propertyId;
//
//    public ListingInputData(String propertyId) {
//        this.propertyId = propertyId;
//    }
    // or should we list all the data
    private final String rentalType;
    private final int daysListedAgo;
    private final float price;
    private final int walkScore;
    private final int numBed;
    private final int numBath;
    private final int squareFeet;
    private final boolean furnished;
    private final boolean parking;
    private final String contact;

    public ListingInputData(String rentalType, int daysListedAgo, float price, int walkScore, int numBed, int numBath,
                    int squareFeet, boolean furnished, boolean parking, String contact) {
        this.rentalType = rentalType;
        this.daysListedAgo = daysListedAgo;
        this.price = price;
        this.walkScore = walkScore;
        this.numBed = numBed;
        this.numBath = numBath;
        this.squareFeet = squareFeet;
        this.furnished = furnished;
        this.parking = parking;
        this.contact = contact;
    }

    public String getRentalType(){
        return rentalType;
    }
    public int getDaysListedAgo(){
        return daysListedAgo;
    }

    public String isFurnished() {
        if (furnished) {
            return "Yes";
        }
        return "No";
    }

    public int getNumBath() {
        return numBath;
    }

    public int getNumBed() {
        return numBed;
    }

    public String isParking() {
        if (parking) {
            return "Yes";
        }
        return "No";
    }

    public float getPrice() {
        return price;
    }

    public int getSquareFeet() {
        return squareFeet;
    }

    public int getWalkScore() {
        return walkScore;
    }

    public String getContact() {
        return contact;
    }
}
