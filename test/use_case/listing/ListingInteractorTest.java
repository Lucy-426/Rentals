package use_case.listing;

import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

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

        ListingOutputBoundary successPresenter = new ListingOutputBoundary() {
            @Override
            public void prepareSuccessView(ListingOutputData listingOutputData) {
                assertEquals(inputData.getId(), "17717493");
                assertNull(propertyDataAccessObject.getRecommendedProperties());

                //successPresenter.prepareSuccessView(listingOutputData);
            }


        };

        ListingInputBoundary interactor = new ListingInteractor(propertyDataAccessObject, successPresenter);

        interactor.execute(inputData);


    }
}