package interface_adapter.homeSearch;

import interface_adapter.ViewManagerModel;
import interface_adapter.listing.ListingState;
import interface_adapter.listing.ListingViewModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomeSearchPresenter implements HomeOutputBoundary {

    private final HomeSearchViewModel homesearchViewModel;

    private final ListingViewModel listingViewModel;

    private ViewManagerModel viewManagerModel;


    // TODO: link listing view model?
    public HomeSearchPresenter(HomeSearchViewModel homesearchViewModel, ListingViewModel listingViewModel, ViewManagerModel viewManagerModel) {
        this.homesearchViewModel = homesearchViewModel;
        this.listingViewModel = listingViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(HomeOutputData homeOutputData) {
        HomeSearchState homeSearchState = homesearchViewModel.getState();
        // TODO: update states for home view
        viewManagerModel.setActiveView(homesearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }
    @Override
    public void prepareListingView(HomeOutputData homeOutputData) {
        ListingState listingState = listingViewModel.getState();
        listingState.setAddress(homeOutputData.getAddress());
        listingState.setNumRooms(homeOutputData.getNumRooms());
        listingState.setPrice(homeOutputData.getPrice());
        listingState.setNumBaths(homeOutputData.getNumBaths());
        listingState.setWalkScore(homeOutputData.getWalkScore());
        listingState.setFurnished(homeOutputData.getFurnished());
        listingState.setListingType(homeOutputData.getListingType());
        this.listingViewModel.setState(listingState);
        listingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
