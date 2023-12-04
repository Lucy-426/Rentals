package interface_adapter.listing;

import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class ListingViewModelTest {

    @Test
    void setState() {
        ListingViewModel listingViewModel = new ListingViewModel();
        ListingState listingState = listingViewModel.getState();
        ListingState test = new ListingState(listingState);
        listingViewModel.setState(test);
        listingViewModel.firePropertyChanged();
        PropertyChangeListener propertyChangeListener = new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {

            }
        };
        listingViewModel.addPropertyChangeListener(propertyChangeListener);
    }
}