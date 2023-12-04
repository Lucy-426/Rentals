package interface_adapter.homeSearch;

import interface_adapter.ViewManagerModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;
import view.HomeSearchView;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HomeSearchPresenterTest {


    @Test
    void prepareSuccessView() {
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSearchViewModel(),
                new ViewManagerModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());

        HashMap<String, String> display = new HashMap<>();
        display.put("155945710", "119 NORTHCREST DR");
        HomeOutputData homeOutputData = new HomeOutputData(display, null, null);

        homeSearchPresenter.prepareSuccessView(homeOutputData);
    }

    @Test
    void displaySignupView() {
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSearchViewModel(),
                new ViewManagerModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());

        homeSearchPresenter.displaySignupView();
    }

    @Test
    void displayLoginView() {
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSearchViewModel(),
                new ViewManagerModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());

        homeSearchPresenter.displayLoginView();
    }

    @Test
    void displayProfile() {
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSearchViewModel(),
                new ViewManagerModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        SavedState savedState = new SavedState();
        homeSearchPresenter.displayProfile(savedState);
    }

    @Test
    void logOut() {
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(new HomeSearchViewModel(),
                new ViewManagerModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        homeSearchPresenter.logOut();
    }
}