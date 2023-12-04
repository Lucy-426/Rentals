package use_case.listing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingOutputDataTest {

    @BeforeAll
    static void init() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
    }
    @Test
    void getId() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getId(), "testID");

    }

    @Test
    void getCity() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getCity(), "New York");
    }

    @Test
    void getAddress() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getAddress(), "123 Smith St");
    }

    @Test
    void getNumRooms() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getNumRooms(), "3");
    }

    @Test
    void getPrice() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getPrice(), "400");
    }

    @Test
    void getNumBaths() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getNumBaths(), "2");
    }

    @Test
    void getWalkScore() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getWalkScore(), "7");
    }

    @Test
    void getFurnished() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getFurnished(), "No");
    }

    @Test
    void getListingType() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertEquals(listingOutputData.getListingType(), "Rental");
    }

    @Test
    void getRecommendations() {
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);

        assertNull(listingOutputData.getRecommendations());
    }
}