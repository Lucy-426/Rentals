package interface_adapter.listing;

import interface_adapter.ViewModel;
import interface_adapter.listing.ListingState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ListingViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Listing View";
    public static final String BACK_BUTTON_LABEL = "Back";
    public ListingViewModel() {
        super("Listing");
    }

    private ListingState state = new ListingState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(ListingState state) {
        this.state = state;
    }

    public ListingState getState() {
        return state;
    }
}
