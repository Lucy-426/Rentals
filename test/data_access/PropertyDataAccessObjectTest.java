package data_access;

import entity.PropertyFactory;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PropertyDataAccessObjectTest {

    private PropertyDataAccessObject propertyDataAccessObject;
    private HashMap<String, String> properties;

    /*
    VERY IMPORTANT: the tests only pass if you delete properties.csv before running each
    test individually; otherwise they fail because coordinates is null
     */

    @BeforeEach
    void init() {
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        properties = new HashMap<>();
        properties.put("34149940", "Address 1");
        properties.put("13302684", "Address 2");
        properties.put("154650151", "Address 3");
        properties.put("17726340", "Address 4");
    }

    @Test
    void getCoordinates() {
        HashMap<Waypoint, String> waypointToID = propertyDataAccessObject.getWaypointToID(properties);
        Set<Waypoint> actual = propertyDataAccessObject.getCoordinates(waypointToID);
        assertEquals(4, actual.size());
    }

    @Test
    void getWaypointToID() {
        HashMap<Waypoint, String> waypointToID = propertyDataAccessObject.getWaypointToID(properties);
        double actual = waypointToID.size();
        assertEquals(4, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"34149940"})
    void getLat(String propertyId) {
        double actual = propertyDataAccessObject.getLat(propertyId);
        assertEquals(getExpectedLat(propertyId), actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"34149940"})
    void getLong(String propertyId) {
        double actual = propertyDataAccessObject.getLong(propertyId);
        assertEquals(getExpectedLong(propertyId), actual);
    }

    // Helper methods to provide expected latitude and longitude based on property ID
    private double getExpectedLat(String propertyId) {
        if (propertyId.equals("34149940")) {
            return 34.018066;
        } else if (propertyId.equals("13302684")) {
            return 34.016518;
        } else if (propertyId.equals("154650151")) {
            return 34.015016;
        } else { // propertyID.equals("17726340")
            return 34.017509;
        }
    }

    private double getExpectedLong(String propertyId) {
        if (propertyId.equals("34149940")) {
            return -118.248139;
        } else if (propertyId.equals("13302684")) {
            return -118.250779;
        } else if (propertyId.equals("154650151")) {
            return -118.263691;
        } else { // propertyID.equals("17726340")
            return -118.248993;
        }
    }
}
