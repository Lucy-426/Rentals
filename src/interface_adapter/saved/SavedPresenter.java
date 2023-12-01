package interface_adapter.saved;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import use_case.listing.ListingOutputBoundary;
import use_case.listing.ListingOutputData;
import use_case.saved.SavedOutputBoundary;
import use_case.saved.SavedOutputData;

public class SavedPresenter implements SavedOutputBoundary {
    private final SavedViewModel savedViewModel;

    private final HomeSearchViewModel homeSearchViewModel;

    private ViewManagerModel viewManagerModel;

    public SavedPresenter(ViewManagerModel viewManagerModel, SavedViewModel savedViewModel,
                          HomeSearchViewModel homeSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.savedViewModel = savedViewModel;
        this.homeSearchViewModel = homeSearchViewModel;
    }


    @Override
    public void prepareSuccessView(SavedOutputData savedOutputData) {
        SavedState savedState = savedViewModel.getState();
        viewManagerModel.setActiveView(savedViewModel.getViewName());
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
        viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
