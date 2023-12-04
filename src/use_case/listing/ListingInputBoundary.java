package use_case.listing;

public interface ListingInputBoundary {
    void execute(ListingInputData listingInputData);

    void saveListing(ListingInputData listingInputData);

}
