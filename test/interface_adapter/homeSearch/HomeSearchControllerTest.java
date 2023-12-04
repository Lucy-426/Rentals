package interface_adapter.homeSearch;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import main.app.HomeSearchUseCaseFactory;
import org.junit.jupiter.api.Test;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HomeSearchControllerTest {

    @Test
    void execute() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(homeSearchViewModel,
                viewManagerModel, new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeSearchPresenter, new PropertyFactory());
        HomeSearchController controller = new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);

        controller.execute(null, null, null, null, null, null, null, null, null);
    }

    @Test
    void displaySignupView() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(homeSearchViewModel,
                viewManagerModel, new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeSearchPresenter, new PropertyFactory());
        HomeSearchController controller = new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);

        controller.displaySignupView();
    }

    @Test
    void displayLoginView() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(homeSearchViewModel,
                viewManagerModel, new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeSearchPresenter, new PropertyFactory());
        HomeSearchController controller = new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);
        controller.displayLoginView();
    }

    @Test
    void displayProfile() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(homeSearchViewModel,
                viewManagerModel, new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeSearchPresenter, new PropertyFactory());
        HomeSearchController controller = new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);
        SavedState state = new SavedState();
        controller.displayProfile(state);
    }

    @Test
    void logOut() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchPresenter homeSearchPresenter = new HomeSearchPresenter(homeSearchViewModel,
                viewManagerModel, new SignupViewModel(), new LoginViewModel(), new SavedViewModel(), new ListingViewModel());
        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeSearchPresenter, new PropertyFactory());
        HomeSearchController controller = new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);
        controller.logOut();
    }
}