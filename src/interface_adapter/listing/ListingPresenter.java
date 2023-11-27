package interface_adapter.listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import use_case.listing.ListingOutputBoundary;
import use_case.listing.ListingOutputData;

public class ListingPresenter implements ListingOutputBoundary{
    private final ListingViewModel listingViewModel;
    private final HomeSearchViewModel homeSearchViewModel;

    private ViewManagerModel viewManagerModel;

    public ListingPresenter(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeSearchViewModel = homeSearchViewModel;
        this.listingViewModel = listingViewModel;
    }


    @Override
    public void prepareSuccessView(ListingOutputData listingOutputData) {
        ListingState listingState = listingViewModel.getState();
        viewManagerModel.setActiveView(listingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
