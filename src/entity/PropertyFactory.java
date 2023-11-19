package entity;

public class PropertyFactory {
    //tentative whether to use since we are getting the property from API?
    public Property create(String rentalType, int daysListedAgo, float price, int walkScore, int numBed,
                           int numBath, int squareFeet, boolean furnished, boolean parking, String contact){
        return new Property(rentalType, daysListedAgo, price, walkScore, numBed, numBath,
        squareFeet, furnished, parking, contact);
    };

//    public class PropertyFactory {
//        public Property create(String address, String rentalType, String contact) {
//            return new Property(address, rentalType, contact);
//        }
//    }

}
