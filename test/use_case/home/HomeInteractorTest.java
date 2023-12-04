package use_case.home;

import data_access.PropertyDataAccessObject;
import entity.Property;
import entity.PropertyFactory;
import interface_adapter.homeSearch.HomeSearchPresenter;
import interface_adapter.saved.SavedState;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class HomeInteractorTest {
    @Test
    void successTest() throws IOException {
        HomeInputData inputData = new HomeInputData("34149940", "LOS ANGELES", "1511 E 23RD ST", "8", "500000+", "4", "all", "Yes", "all");
//        HomeSearchDataAccessInterface propertyRepository = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        PropertyDataAccessObject propertyDataAccessObject;
        PropertyFactory propertyFactory = new PropertyFactory();
        SavedState savedState = new SavedState();

        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", propertyFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // This creates a successPresenter that tests whether the test case is as we expect.
        HomeOutputBoundary successPresenter = new HomeOutputBoundary()
        {
            @Override
            public void prepareSuccessView(HomeOutputData homeOutputData) {
                // 2 things to check: the output data is correct, and the user has been created in the DAO.
                HashMap<String, String> properties = homeOutputData.getDisplayedProperties();
                assertEquals(inputData.getAddress(), properties.get("34149940"));
            }

            @Override
            public void displaySignupView() {


            }

            @Override
            public void displayLoginView() {

            }

            @Override
            public void displayProfile(SavedState savedState) {

            }

            @Override
            public void logOut() {

            }

        };

        HomeInputBoundary interactor = new HomeInteractor(propertyDataAccessObject, successPresenter, propertyFactory);

        interactor.execute(inputData);
        interactor.displayLoginView();
        interactor.logOut();
        interactor.displaySignupView();
        interactor.displayProfile(savedState);

    }
}