package use_case.listing;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.Property;
import entity.PropertyFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

class ListingInteractorTest {


    @Test
    void successTest() {
        ListingInputData inputData = new ListingInputData("17717493");
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

        ListingOutputBoundary successPresenter = new ListingOutputBoundary() {
            @Override
            public void prepareSuccessView(ListingOutputData listingOutputData) {
                assertEquals(inputData.getId(), "17717493");
            }

            @Override
            public void listingSaved(String msg) {

            }


        };
        Property property = propertyDataAccessObject.getProperty(inputData.getId());
        propertyDataAccessObject.save(property);
        propertyDataAccessObject.filter();
        propertyDataAccessObject.makeRecommendations(property);
        ListingInputBoundary interactor = new ListingInteractor(propertyDataAccessObject, userDataAccessObject, successPresenter);
        interactor.execute(inputData);


    }
}