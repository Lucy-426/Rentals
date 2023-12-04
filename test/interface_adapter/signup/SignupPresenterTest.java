package interface_adapter.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;


import static org.junit.jupiter.api.Assertions.*;

class SignupPresenterTest {

    @Test
    void prepareSuccessView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, homeSearchViewModel, signupViewModel, loginViewModel);
        SignupOutputData signupOutputData = new SignupOutputData("username", "2023-12-03T20:54:20.658583", false);
        signupPresenter.prepareSuccessView(signupOutputData);

    }

    @Test
    void prepareFailView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, homeSearchViewModel, signupViewModel, loginViewModel);
        signupPresenter.prepareFailView("error");

    }

    @Test
    void displayHome() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, homeSearchViewModel, signupViewModel, loginViewModel);
        signupPresenter.displayHome();

    }
}