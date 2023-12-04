package use_case.listing;

public interface ListingOutputBoundary {
    void prepareSuccessView(ListingOutputData listingOutputData);

    void listingSaved(String msg);

}
