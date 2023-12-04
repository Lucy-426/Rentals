package interface_adapter.signup;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInteractor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupControllerTest {

    @Test
    void execute() {

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, homeSearchViewModel, signupViewModel, loginViewModel);

        SignupInteractor signupInteractor = new SignupInteractor(userDataAccessObject, signupPresenter, new CommonUserFactory());
        SignupController signupController = new SignupController(signupInteractor);

        signupController.execute("testUsername", "testPassword1", "testPassword2");
    }

    @Test
    void displayHome() {
        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupPresenter signupPresenter = new SignupPresenter(viewManagerModel, homeSearchViewModel, signupViewModel, loginViewModel);

        SignupInteractor signupInteractor = new SignupInteractor(userDataAccessObject, signupPresenter, new CommonUserFactory());
        SignupController signupController = new SignupController(signupInteractor);
        signupController.displayHome();
    }
}