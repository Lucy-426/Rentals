package interface_adapter.saved;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingState;
import interface_adapter.listing.ListingViewModel;
import use_case.listing.ListingOutputBoundary;
import use_case.listing.ListingOutputData;
import use_case.saved.SavedOutputBoundary;
import use_case.saved.SavedOutputData;

public class SavedPresenter implements SavedOutputBoundary {
    private final ListingViewModel listingViewModel;

    private final HomeSearchViewModel homeSearchViewModel;

    private ViewManagerModel viewManagerModel;

    public SavedPresenter(ViewManagerModel viewManagerModel, ListingViewModel listingViewModel,
                          HomeSearchViewModel homeSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.listingViewModel = listingViewModel;
        this.homeSearchViewModel = homeSearchViewModel;
    }


    @Override
    public void prepareSuccessView(SavedOutputData savedOutputData) {
        ListingState listingState = listingViewModel.getState();
        listingState.setId(savedOutputData.getId());
        listingState.setCity(savedOutputData.getCity());
        listingState.setAddress(savedOutputData.getAddress());
        listingState.setNumRooms(savedOutputData.getNumRooms());
        listingState.setPrice(savedOutputData.getPrice());
        listingState.setNumBaths(savedOutputData.getNumBaths());
        listingState.setWalkScore(savedOutputData.getWalkScore());
        listingState.setFurnished(savedOutputData.getFurnished());
        listingState.setListingType(savedOutputData.getListingType());
        listingState.setRecommendations(savedOutputData.getRecommendations());

        this.listingViewModel.setState(listingState);
        listingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(listingViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }

    @Override
    public void displayHome() {
        HomeSearchState homeSearchState = homeSearchViewModel.getState();
        this.homeSearchViewModel.setState(homeSearchState);
        homeSearchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void logOut() {
        HomeSearchState homeSearchState = homeSearchViewModel.getState();
        homeSearchState.setLoggedIn(false);
        this.homeSearchViewModel.setState(homeSearchState);
        this.homeSearchViewModel.firePropertyChanged();
        ListingState listingState = listingViewModel.getState();
        listingState.setLoggedIn(false);
        listingState.setUsername("");
        this.listingViewModel.setState(listingState);
        this.listingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
