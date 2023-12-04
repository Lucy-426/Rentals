package interface_adapter.login;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInteractor;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

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
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, homeSearchViewModel, loginViewModel, listingViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        LoginController loginController = new LoginController(loginInteractor);

        loginController.execute("testUsername", "testPassword");
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
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, homeSearchViewModel, loginViewModel, listingViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);

        LoginController loginController = new LoginController(loginInteractor);
        loginController.displayHome();
    }
}