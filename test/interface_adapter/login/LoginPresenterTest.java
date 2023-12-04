package interface_adapter.login;

import entity.Property;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import use_case.login.LoginOutputData;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class LoginPresenterTest {

    @Test
    void prepareSuccessView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, homeSearchViewModel, loginViewModel,
                listingViewModel);

        HashMap<String, Property> listings = new HashMap<>();
        LoginOutputData loginOutputData = new LoginOutputData("username", listings);
        loginPresenter.prepareSuccessView(loginOutputData);
    }

    @Test
    void prepareFailView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, homeSearchViewModel, loginViewModel,
                listingViewModel);
        loginPresenter.prepareFailView("testError");
    }

    @Test
    void displayHome() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(viewManagerModel, homeSearchViewModel, loginViewModel,
                listingViewModel);
        loginPresenter.displayHome();
    }
}