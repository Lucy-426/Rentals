package use_case.CenterMap;

import data_access.MapDataAccessObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CenterMapInteractorTest {
    @Test
    void successTest() {
        CenterMapInputData inputData = new CenterMapInputData("1 Austin Terrace, Toronto, ON M5R 1X8");
        MapDataAccessObject mapDataAccessObject = new MapDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        CenterMapOutputBoundary successPresenter = new CenterMapOutputBoundary() {
            @Override
            public void prepareSuccessView(CenterMapOutputData centerMapOutputData) {
                double actualLat = centerMapOutputData.getGeoPosition().getLatitude();
                double actualLng = centerMapOutputData.getGeoPosition().getLongitude();
                String actual = actualLat + "," + actualLng;
                assertEquals("43.6780371,-79.4094439", actual);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        CenterMapInputBoundary interactor = new CenterMapInteractor(mapDataAccessObject, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        CenterMapInputData inputData = new CenterMapInputData("Fake Address 9999");
        MapDataAccessObject mapDataAccessObject = new MapDataAccessObject();

        CenterMapOutputBoundary successPresenter = new CenterMapOutputBoundary() {
            @Override
            public void prepareSuccessView(CenterMapOutputData centerMapOutputData) {
                fail("Use case success is unexpected");
            }

            @Override
            public void prepareFailView(String error) {

            }
        };
    }
}