package interface_adapter.saved;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import org.junit.jupiter.api.Test;
import use_case.saved.SavedInteractor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SavedControllerTest {

    @Test
    void execute() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        SavedInteractor savedInteractor = new SavedInteractor(propertyDataAccessObject, savedPresenter);
        SavedController savedController = new SavedController(savedInteractor);

        savedController.execute("140996203");
    }

    @Test
    void displayHome() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        SavedInteractor savedInteractor = new SavedInteractor(propertyDataAccessObject, savedPresenter);
        SavedController savedController = new SavedController(savedInteractor);

        savedController.displayHome();
    }

    @Test
    void logOut() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        SavedInteractor savedInteractor = new SavedInteractor(propertyDataAccessObject, savedPresenter);
        SavedController savedController = new SavedController(savedInteractor);

        savedController.logOut();
    }
}