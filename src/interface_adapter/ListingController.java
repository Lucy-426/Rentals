package interface_adapter;

import use_case.listing.ListingInputBoundary;

public class ListingController {
    final ListingInputBoundary listingInteractor;

    public ListingController(ListingInputBoundary listingInteractor) {
        this.listingInteractor = listingInteractor;
    }

}
