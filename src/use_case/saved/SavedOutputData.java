package use_case.saved;

public class SavedOutputData {

    // edit to include only necessary variables
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

    public SavedOutputData(String rentalType, int daysListedAgo, float price, int walkScore, int numBed, int numBath,
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

}
