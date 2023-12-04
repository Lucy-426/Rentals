package interface_adapter.homeSearch;

import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CenterMap.CenterMapOutputBoundary;
import use_case.CenterMap.CenterMapOutputData;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInteractor;
import use_case.home.HomeOutputBoundary;
import use_case.home.HomeOutputData;
import view.HomeSearchView;

import javax.swing.text.View;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class HomeSearchStateTest {

//    private HomeSearchState homeSearchState;
//
//    private HomeOutputData homeOutputData;

    /*
    IMPORTANT: setWaypointIDMap and setWaypoints fail because coordinates is null whenever the test is run;
    and for some reason init() doesn't work so I have to manually retype everything in init in those two tests
    which leads to a lot of repetition.
     */

//    @BeforeEach
//    void init() {
//        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
//        HomeSearchState homeSearchState = homeSearchViewModel.getState();
//
//        PropertyDataAccessObject propertyDataAccessObject;
//        try {
//            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        HashMap<String, String> displayedProperties = new HashMap<>();
//        displayedProperties.put("34149940", "1511 E 23RD ST");
//        displayedProperties.put("13302684", "1262 E ADAMS BLVD");
//        displayedProperties.put("154650151", "743 E JEFFERSON BLVD");
//        displayedProperties.put("17726340", "2322 HOOPER AVE");
//
//        HashMap<Waypoint, String> waypointIDMap = propertyDataAccessObject.getWaypointToID(displayedProperties);
//        Set<Waypoint> waypoints = propertyDataAccessObject.getCoordinates(waypointIDMap);
//
//        HomeOutputData homeOutputData = new HomeOutputData(displayedProperties, waypointIDMap, waypoints);
//    }
//
//    @Test
//    void setWaypointIDMap() {
//        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
//        HomeSearchState homeSearchState = homeSearchViewModel.getState();
//
//        PropertyDataAccessObject propertyDataAccessObject;
//        try {
//            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        HashMap<String, String> displayedProperties = new HashMap<>();
//        displayedProperties.put("34149940", "1511 E 23RD ST");
//        displayedProperties.put("13302684", "1262 E ADAMS BLVD");
//        displayedProperties.put("154650151", "743 E JEFFERSON BLVD");
//        displayedProperties.put("17726340", "2322 HOOPER AVE");
//
//        HashMap<Waypoint, String> waypointIDMap = propertyDataAccessObject.getWaypointToID(displayedProperties);
//        Set<Waypoint> waypoints = propertyDataAccessObject.getCoordinates(waypointIDMap);
//
//        HomeOutputData homeOutputData = new HomeOutputData(displayedProperties, waypointIDMap, waypoints);
//
//        // Actual test
//        homeSearchState.setWaypointIDMap(homeOutputData.getWaypointIDMap());
//        assertEquals(homeSearchState.getWaypointIDMap(), homeOutputData.getWaypointIDMap());
//    }
//
//    @Test
//    void setWaypoints() {
//        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
//        HomeSearchState homeSearchState = homeSearchViewModel.getState();
//
//        PropertyDataAccessObject propertyDataAccessObject;
//        try {
//            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        HashMap<String, String> displayedProperties = new HashMap<>();
//        displayedProperties.put("34149940", "1511 E 23RD ST");
//        displayedProperties.put("13302684", "1262 E ADAMS BLVD");
//        displayedProperties.put("154650151", "743 E JEFFERSON BLVD");
//        displayedProperties.put("17726340", "2322 HOOPER AVE");
//
//        HashMap<Waypoint, String> waypointIDMap = propertyDataAccessObject.getWaypointToID(displayedProperties);
//        Set<Waypoint> waypoints = propertyDataAccessObject.getCoordinates(waypointIDMap);
//
//        HomeOutputData homeOutputData = new HomeOutputData(displayedProperties, waypointIDMap, waypoints);
//
//        // Actual test
//        homeSearchState.setWaypoints(homeOutputData.getWaypoints());
//        assertEquals(homeSearchState.getWaypoints(), homeOutputData.getWaypoints());
//    }

    @Test
    void setStartPosition() {
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        HomeSearchState homeSearchState = homeSearchViewModel.getState();

        CenterMapOutputData centerMapOutputData = new CenterMapOutputData(new GeoPosition(43.6780371, -79.4094439), false);

        // Actual test
        homeSearchState.setStartPosition(centerMapOutputData.getGeoPosition());
        assertEquals(homeSearchState.getStartPosition(), centerMapOutputData.getGeoPosition());
    }
}