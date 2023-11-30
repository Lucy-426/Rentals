package interface_adapter.savedListings;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class SavedViewModel extends ViewModel {
    public final String TITLE_LABEL = "Saved Listings View";
    public SavedViewModel(String viewName) {
        super("Saved listings");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
