package interface_adapter;

import use_case.listing.ListingOutputBoundary;
import use_case.listing.ListingOutputData;

public class ListingPresenter implements ListingOutputBoundary{
    private final ListingViewModel listingViewModel;
    private ViewManagerModel viewManagerModel;

    public ListingPresenter(ViewManagerModel viewManagerModel, ListingViewModel listingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.listingViewModel = listingViewModel;

    }


    @Override
    public void prepareSuccessView(ListingOutputData listingOutputData) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
