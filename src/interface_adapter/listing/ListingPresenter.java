package interface_adapter.listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
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
        listingState.setId(listingOutputData.getId());
        listingState.setCity(listingOutputData.getCity());
        listingState.setAddress(listingOutputData.getAddress());
        listingState.setNumRooms(listingOutputData.getNumRooms());
        listingState.setPrice(listingOutputData.getPrice());
        listingState.setNumBaths(listingOutputData.getNumBaths());
        listingState.setWalkScore(listingOutputData.getWalkScore());
        listingState.setFurnished(listingOutputData.getFurnished());
        listingState.setListingType(listingOutputData.getListingType());
        listingState.setRecommendations(listingOutputData.getRecommendations());

        this.listingViewModel.setState(listingState);
        listingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void listingSaved(String msg) {
        ListingState listingState = listingViewModel.getState();
        listingState.setSaveMsg(msg);
        listingViewModel.firePropertyChanged();
    }
}
