package interface_adapter.homeSearch;

import interface_adapter.saved.SavedState;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.home.HomeOutputData;

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

    }



