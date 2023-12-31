package interface_adapter.listing;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import org.junit.jupiter.api.Test;
import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInteractor;
import use_case.listing.ListingOutputBoundary;

import java.io.IOException;

class ListingControllerTest {

    @Test
    void execute() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();

        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ListingOutputBoundary listingOutputBoundary = new ListingPresenter(viewManagerModel, listingViewModel);

        ListingInputBoundary listingInteractor = new ListingInteractor(propertyDataAccessObject, userDataAccessObject, listingOutputBoundary);

        ListingController listingController = new ListingController(listingInteractor, homeSearchViewModel,viewManagerModel);
        listingController.execute("201847552");


    }

    @Test
    void switchView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();

        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ListingOutputBoundary listingOutputBoundary = new ListingPresenter(viewManagerModel, listingViewModel);

        ListingInputBoundary listingInteractor = new ListingInteractor(propertyDataAccessObject, userDataAccessObject, listingOutputBoundary);

        ListingController listingController = new ListingController(listingInteractor, homeSearchViewModel,viewManagerModel);
        listingController.switchView();

    }
}