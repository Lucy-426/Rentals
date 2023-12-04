package entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {
    private Property property;

    @BeforeEach
    void setUp() {
        property = new Property("1008933950", "Los Angeles", "717 Bay St", "2", "100000", "1", "50", "Yes", "House");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getID() {
        assertEquals("1008933950", property.getID());
    }

    @Test
    void getCity() {
        assertEquals("Los Angeles", property.getCity());

    }

    @Test
    void getAddress() {
        assertEquals("717 Bay St", property.getAddress());
    }

    @Test
    void getNumRooms() {
        assertEquals("2", property.getNumRooms());
    }

    @Test
    void getPriceRange() {
        assertEquals("100000", property.getPriceRange());
    }

    @Test
    void getNumBaths() {
        assertEquals("1", property.getNumBaths());
    }

    @Test
    void getWalkScore() {
        assertEquals("50", property.getWalkScore());
    }

    @Test
    void getFurnished() {
        assertEquals("Yes", property.getFurnished());
    }

    @Test
    void getListingType() {
        assertEquals("House", property.getListingType());
    }
}