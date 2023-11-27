package data_access;

import entity.Listing;
import entity.ListingFactory;
import use_case.listing.ListingDataAccessInterface;

public class ListingDataAccessObject implements ListingDataAccessInterface {
    // TODO: check if this is needed?
    private ListingFactory listingFactory;

    private Listing listing;

    public ListingDataAccessObject(ListingFactory listingFactory) {
        this.listingFactory = listingFactory;
    }

    public Listing getListing() {
        return listing;
    }
}
