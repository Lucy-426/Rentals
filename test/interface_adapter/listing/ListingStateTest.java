package interface_adapter.listing;

import org.junit.jupiter.api.Test;
import use_case.listing.ListingOutputData;

import static org.junit.Assert.assertEquals;

class ListingStateTest {

    @Test
    void setId() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setId(listingOutputData.getId());
        assertEquals(listingState.getId(), "testID");
    }

    @Test
    void setCity() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setCity(listingOutputData.getCity());
        assertEquals(listingState.getCity(), "New York");
    }

    @Test
    void setAddress() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setAddress(listingOutputData.getAddress());
        assertEquals(listingState.getAddress(), "123 Smith St");
    }

    @Test
    void setNumRooms() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setNumRooms(listingOutputData.getNumRooms());
        assertEquals(listingState.getNumRooms(), "3");
    }

    @Test
    void setPrice() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setPrice(listingOutputData.getPrice());
        assertEquals(listingState.getPrice(), "400");
    }

    @Test
    void setNumBaths() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setNumBaths(listingOutputData.getNumBaths());
        assertEquals(listingState.getNumBaths(), "2");
    }

    @Test
    void setWalkScore() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setWalkScore(listingOutputData.getWalkScore());
        assertEquals(listingState.getWalkScore(), "7");
        ListingState test = new ListingState(listingState);

    }

    @Test
    void setFurnished() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setFurnished(listingOutputData.getFurnished());
        assertEquals(listingState.getFurnished(), "No");
    }

    @Test
    void setListingType() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setListingType(listingOutputData.getListingType());
        assertEquals(listingState.getListingType(), "Rental");
    }

    @Test
    void setRecommendations() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingState.setRecommendations(listingOutputData.getRecommendations());
        assertEquals(listingState.getRecommendations(), null);
    }
}