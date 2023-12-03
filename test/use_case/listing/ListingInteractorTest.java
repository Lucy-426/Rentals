package use_case.listing;

import data_access.PropertyDataAccessObject;
import entity.Property;
import entity.PropertyFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class ListingInteractorTest {


    @Test
    void successTest() {
        ListingInputData inputData = new ListingInputData("testID");
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ListingOutputBoundary successPresenter = new ListingOutputBoundary() {
            @Override
            public void prepareSuccessView(ListingOutputData listingOutputData) {
                assertEquals(inputData.getId(), "testID");
                assertNull(propertyDataAccessObject.getRecommendedProperties());

                //successPresenter.prepareSuccessView(listingOutputData);
            }


        };

        ListingInputBoundary interactor = new ListingInteractor(propertyDataAccessObject, successPresenter);
        interactor.execute(inputData);
        Property property = new Property("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental");
        ListingOutputData listingOutputDataTest = new ListingOutputData(property.getID(), property.getCity(), property.getAddress(), property.getNumRooms(),
                property.getPriceRange(), property.getNumBaths(), property.getWalkScore(), property.getFurnished(), property.getListingType(),
                null);

    }
}