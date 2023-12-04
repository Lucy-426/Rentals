package interface_adapter.saved;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SavedViewModel extends ViewModel {

    public static final String TITLE_LABEL = "Saved View";
    public static final String BACK_BUTTON_LABEL = "Back";
    public SavedViewModel() {
        super("Saved");
    }

    private SavedState state = new SavedState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(SavedState state) {
        this.state = state;
    }

    public SavedState getState() {
        return state;
    }
}
