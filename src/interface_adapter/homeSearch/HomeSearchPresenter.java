package interface_adapter.homeSearch;

import interface_adapter.ViewManagerModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomeSearchPresenter implements HomeOutputBoundary {

    private final HomeSearchViewModel homesearchViewModel;

    private ViewManagerModel viewManagerModel;


    public HomeSearchPresenter(HomeSearchViewModel homesearchViewModel, ViewManagerModel viewManagerModel) {
        this.homesearchViewModel = homesearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(HomeOutputData homeOutputData) {
        HomeSearchState homeSearchState = homesearchViewModel.getState();
        homeSearchState.setDisplayedListings(homeOutputData.getDisplayedProperties());
        homesearchViewModel.setState(homeSearchState);
        homesearchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homesearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();


    }

}
