package interface_adapter.listing;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class ListingViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Listing View";

    public ListingViewModel() {
        super("listing view");
    }


    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
