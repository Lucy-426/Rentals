package interface_adapter.listing;

import org.junit.jupiter.api.Test;

class ListingViewModelTest {

    @Test
    void setState() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingState test = new ListingState(listingState);
        listingViewModel.setState(test);
        listingViewModel.firePropertyChanged();
    }
}