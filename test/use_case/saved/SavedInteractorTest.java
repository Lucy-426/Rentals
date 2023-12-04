package use_case.saved;

import data_access.PropertyDataAccessObject;
import entity.Property;
import entity.PropertyFactory;
import org.junit.jupiter.api.Test;
import use_case.home.HomeSearchDataAccessInterface;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SavedInteractorTest {

    @Test
    void execute() throws IOException {
        HomeSearchDataAccessInterface properties = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());

        Property property = new Property("155945710", "SOUTH SAN FRANCISCO", "119 NORTHCREST DR", "3", "554334",
                "2", "60", "Mo", "Single Family Residence / Townhouse");

        properties.save(property);
        properties.filter();
        SavedOutputBoundary successPresenter = new SavedOutputBoundary() {
            @Override
            public void prepareSuccessView(SavedOutputData savedOutputData) {
                assertEquals("155945710", savedOutputData.getId());
                assertEquals("SOUTH SAN FRANCISCO", savedOutputData.getCity());
                assertEquals("119 NORTHCREST DR", savedOutputData.getAddress());
                assertEquals("3", savedOutputData.getNumRooms());
                assertEquals("554334", savedOutputData.getPrice());
                assertEquals("2", savedOutputData.getNumBaths());
                assertEquals("60", savedOutputData.getWalkScore());
                assertEquals("No", savedOutputData.getFurnished());
                assertEquals("Single Family Residence / Townhouse", savedOutputData.getListingType());

            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected");

            }

            @Override
            public void displayHome() {

            }

            @Override
            public void logOut() {

            }
        };

        SavedInputData savedInputData = new SavedInputData("155945710");
        SavedInputBoundary savedInteractor = new SavedInteractor(properties, successPresenter);
        savedInteractor.execute(savedInputData);
    }

    @Test
    void displayHome() {
    }

    @Test
    void logOut() {
    }
}