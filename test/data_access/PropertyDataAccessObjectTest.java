package data_access;

import entity.PropertyFactory;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

class PropertyDataAccessObjectTest {

    private PropertyDataAccessObject propertyDataAccessObject;

    private HashMap<String, String> properties;

    PropertyDataAccessObjectTest(HashMap<String, String> properties) {
        this.properties = properties;
    }

    @BeforeEach
    void init() {
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HashMap<String, String> properties = new HashMap<>();
        properties.put("34149940", "Address 1");
        properties.put("13302684", "Address 2");
        properties.put("154650151", "Address 3");
        properties.put("17726340", "Address 4");
    }

//    @Test
//    void getCoordinates() {
//        HashMap<Waypoint, String> waypointToID = propertyDataAccessObject.getWaypointToID(properties);
//        Set<Waypoint> actual = propertyDataAccessObject.getCoordinates(waypointToID);
//        assertEquals(4, actual.size());
//    }
//
//    @Test
//    void getWaypointToID() {
//        HashMap<Waypoint, String> waypointToID = propertyDataAccessObject.getWaypointToID(properties);
//        double actual = waypointToID.size();
//        assertEquals(4, actual);
//    }

    @Test
    void getLat() {
        double actual = propertyDataAccessObject.getLat("34149940");
        assertEquals(34.018066, actual);
    }

    @Test
    void getLong() {
        double actual = propertyDataAccessObject.getLong("34149940");
        assertEquals(-118.248139, actual);
    }
}