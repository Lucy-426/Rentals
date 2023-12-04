package interface_adapter.homeSearch;

import interface_adapter.ViewManagerModel;
import interface_adapter.listing.ListingState;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomeSearchPresenter implements HomeOutputBoundary {

    private final HomeSearchViewModel homesearchViewModel;

    private ViewManagerModel viewManagerModel;

    private SignupViewModel signupViewModel;

    private LoginViewModel loginViewModel;

    private SavedViewModel savedViewModel;

    private ListingViewModel listingViewModel;

    public HomeSearchPresenter(HomeSearchViewModel homesearchViewModel, ViewManagerModel viewManagerModel,
                               SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                               SavedViewModel savedViewModel, ListingViewModel listingViewModel) {
        this.homesearchViewModel = homesearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.savedViewModel = savedViewModel;
        this.listingViewModel = listingViewModel;
    }
    @Override
    public void prepareSuccessView(HomeOutputData homeOutputData) {
        HomeSearchState homeSearchState = homesearchViewModel.getState();
        homeSearchState.setDisplayedListings(homeOutputData.getDisplayedProperties());
        homeSearchState.setWaypoints(homeOutputData.getWaypoints());
        homeSearchState.setWaypointIDMap(homeOutputData.getWaypointIDMap());
        homesearchViewModel.setState(homeSearchState);
        homesearchViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(homesearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void displaySignupView() {
        this.signupViewModel.setState(new SignupState());
        signupViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(signupViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void displayLoginView() {
        this.loginViewModel.setState(new LoginState());
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void displayProfile(SavedState savedState) {
        this.savedViewModel.setState(savedState);
        savedViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(savedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void logOut() {
        HomeSearchState homeSearchState = homesearchViewModel.getState();
        homeSearchState.setLoggedIn(false);
        this.homesearchViewModel.setState(homeSearchState);
        this.homesearchViewModel.firePropertyChanged();
        ListingState listingState = listingViewModel.getState();
        listingState.setLoggedIn(false);
        listingState.setUsername("");
        this.listingViewModel.setState(listingState);
        this.listingViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homesearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
