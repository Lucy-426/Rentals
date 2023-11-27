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


    // TODO: can get rid of listing view model here?
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

}
