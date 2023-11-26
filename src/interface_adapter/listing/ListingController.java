package interface_adapter.listing;

import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInputData;

public class ListingController {
    final ListingInputBoundary listingInteractor;

    public ListingController(ListingInputBoundary listingInteractor) {
        this.listingInteractor = listingInteractor;
    }

    public void execute(String id, String city, String rentalType, int daysListedAgo, float price, int walkScore, int numBed, int numBath,
                        int squareFeet, boolean furnished, boolean parking, String contact){
        ListingInputData listingInputData = new ListingInputData(id, city, rentalType, daysListedAgo, price, walkScore, numBed, numBath,
                squareFeet, furnished, parking, contact);

        listingInteractor.execute(listingInputData);
    }
}
