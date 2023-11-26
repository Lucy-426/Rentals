package entity;

public class Listing {
    private final String id;
    private final String city;
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

    public Listing(String id, String city, String rentalType, int daysListedAgo, float price, int walkScore, int numBed, int numBath,
                    int squareFeet, boolean furnished, boolean parking, String contact) {
        this.id = id;
        this.city = city;
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
    public String getId(){
        return id;
    }
    public String getCity(){
        return city;
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
