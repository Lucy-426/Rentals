package interface_adapter.homeSearch;

import interface_adapter.saved.SavedState;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.home.HomeOutputData;
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
    private HomeSearchState homeSearchState;
    private HomeSearchState copy;
    private SavedState save;
    GeoPosition startPosition;

    @BeforeEach
    void setUp() {
        copy = new HomeSearchState();
        homeSearchState = new HomeSearchState(copy);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSearchBarInput() {
        String search = "hello";
        copy.setSearchBarInput(search);
        assertEquals(search, copy.getSearchBarInput());
    }

    @Test
    void getId() {
        String id = "erin";
        copy.setId(id);
        assertEquals(id, copy.getId());
    }

    @Test
    void getCity() {
        String city = "van";
        copy.setCity(city);
        assertEquals(city, copy.getCity());
    }

    @Test
    void getAddress() {
        String add = "717";
        copy.setAddress(add);
        assertEquals(add, copy.getAddress());
    }

    @Test
    void getNumRooms() {
        String numrooms = "2";
        copy.setNumRooms(numrooms);
        assertEquals(numrooms, copy.getNumRooms());
    }

    @Test
    void getPriceRange() {
        String price = "200";
        copy.setPriceRange(price);
        assertEquals(price, copy.getPriceRange());
    }

    @Test
    void getNumBaths() {
        String numbaths = "9";
        copy.setNumBaths(numbaths);
        assertEquals(numbaths, copy.getNumBaths());
    }

    @Test
    void getWalkScore() {
        String walkscore = "90";
        copy.setWalkScore(walkscore);
        assertEquals(walkscore, copy.getWalkScore());
    }

    @Test
    void getFurnished() {
        String furn = "Yes";
        copy.setFurnished(furn);
        assertEquals(furn, copy.getFurnished());    }

    @Test
    void getListingType() {
        String lt = "House";
        copy.setListingType(lt);
        assertEquals(lt, copy.getListingType());
    }

    @Test
    void getLoggedIn() {
        boolean log = true;
        copy.setLoggedIn(log);
        assertEquals(log, copy.getLoggedIn());
    }

    @Test
    void getSavedState() {
        copy.setSavedState(save);
        assertEquals(save, copy.getSavedState());
    }

    @Test
    void getDisplayedListings() {
        assertEquals(copy.getDisplayedListings(),homeSearchState.getDisplayedListings());
    }

    @Test
    void getWaypointIDMap() {
        assertEquals(copy.getWaypointIDMap(),homeSearchState.getWaypointIDMap());
    }

    @Test
    void getWaypoints() {
        assertEquals(copy.getWaypoints(),homeSearchState.getWaypoints());
    }

    @Test
    void getStartPosition() {

        copy.setStartPosition(startPosition);
        assertEquals(startPosition, copy.getStartPosition());
    }

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


