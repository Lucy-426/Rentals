package interface_adapter.homeSearch;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;

public class HomeSearchPresenter implements HomeOutputBoundary {

    private final HomeSearchViewModel homesearchViewModel;

    private ViewManagerModel viewManagerModel;

    private SignupViewModel signupViewModel;

    private LoginViewModel loginViewModel;

    // TODO: link listing view model?
    public HomeSearchPresenter(HomeSearchViewModel homesearchViewModel, ViewManagerModel viewManagerModel,
                               SignupViewModel signupViewModel, LoginViewModel loginViewModel) {
        this.homesearchViewModel = homesearchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
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
}
