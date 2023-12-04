package use_case.listing;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListingInputDataTest {
    @BeforeAll
    static void init() {
        ListingInputData listingInputData = new ListingInputData("testID");
    }
    @Test
    void getId() {
        ListingInputData listingInputData = new ListingInputData("testID");
        assertEquals(listingInputData.getId(), "testID");
    }
}