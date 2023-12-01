package interface_adapter.homeSearch;

import interface_adapter.ViewManagerModel;
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

    // TODO: link listing view model?
    public HomeSearchPresenter(HomeSearchViewModel homesearchViewModel, ViewManagerModel viewManagerModel,
                               SignupViewModel signupViewModel, LoginViewModel loginViewModel, SavedViewModel savedViewModel) {
        this.homesearchViewModel = homesearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.savedViewModel = savedViewModel;
    }
    @Override
    public void prepareSuccessView(HomeOutputData homeOutputData) {
        HomeSearchState homeSearchState = homesearchViewModel.getState();
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
        System.out.println("home: " + savedState.getUsername());
        savedViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(savedViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void logOut() {
        HomeSearchState homeSearchState = homesearchViewModel.getState();
        homeSearchState.setLoggedIn(false);
        this.homesearchViewModel.setState(homeSearchState);
        this.homesearchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homesearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
