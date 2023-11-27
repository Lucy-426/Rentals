package entity;

public class ListingFactory {
    public Listing create(String rentalType, int daysListedAgo, float price, int walkScore, int numBed,
                           int numBath, int squareFeet, boolean furnished, boolean parking, String contact){
        return new Listing(rentalType, daysListedAgo, price, walkScore, numBed, numBath, furnished, parking, contact);
    };

}
