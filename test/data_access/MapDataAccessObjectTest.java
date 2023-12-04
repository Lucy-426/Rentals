package data_access;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import data_access.MapDataAccessObject;

import static org.junit.jupiter.api.Assertions.*;

class MapDataAccessObjectTest {

    private MapDataAccessObject mapDataAccessObject = new MapDataAccessObject();

    @Test
    void getLat() {
        assertEquals(43.6780371, mapDataAccessObject.getLat("1 Austin Terrace, Toronto, ON M5R 1X8"));
    }

    @Test
    void getLong() {
        assertEquals(-79.4094439, mapDataAccessObject.getLong("1 Austin Terrace, Toronto, ON M5R 1X8"));
    }
}