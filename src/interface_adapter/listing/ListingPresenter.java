package interface_adapter.listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
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
        ListingState listingState = listingViewModel.getState();
        listingState.setAddress(listingOutputData.getAddress());
        listingState.setNumRooms(listingOutputData.getNumRooms());
        listingState.setPrice(listingOutputData.getPrice());
        listingState.setNumBaths(listingOutputData.getNumBaths());
        listingState.setWalkScore(listingOutputData.getWalkScore());
        listingState.setFurnished(listingOutputData.getFurnished());
        listingState.setListingType(listingOutputData.getListingType());
        this.listingViewModel.setState(listingState);
        listingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
