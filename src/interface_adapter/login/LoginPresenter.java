package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;
import view.SavedView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginPresenter implements LoginOutputBoundary {

    private final HomeSearchViewModel homeSearchViewModel;
    private final LoginViewModel loginViewModel;
    private final SavedViewModel savedViewModel;
    private ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          HomeSearchViewModel homeSearchViewModel,
                          LoginViewModel loginViewModel,
                          SavedViewModel savedViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.homeSearchViewModel = homeSearchViewModel;
        this.loginViewModel = loginViewModel;
        this.savedViewModel = savedViewModel;
    }

    //@Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, create saved state based on the logged in user's data
        SavedState savedState = new SavedState();
        savedState.setUsername(response.getUsername());
        savedState.setSavedListings(response.getSavedListings());
        // On success, go to homeView showing profile and logout button
        HomeSearchState homeSearchState = homeSearchViewModel.getState();
        homeSearchState.setLoggedIn(true);
        homeSearchState.setSavedState(savedState);
        this.homeSearchViewModel.setState(homeSearchState);
        this.homeSearchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    public void displayHome() {
        HomeSearchState homeSearchState = homeSearchViewModel.getState();
        this.homeSearchViewModel.setState(homeSearchState);
        homeSearchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
