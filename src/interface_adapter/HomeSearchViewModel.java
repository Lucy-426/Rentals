package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeSearchViewModel extends ViewModel{
    public final String TITLE_LABEL = "Sign Up View";

    public final String SEARCH_BAR_LABEL = "Enter a city, address, or listing ID.";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    public HomeSearchViewModel() {
        super("sign up");
    }

    private HomeSearchState state = new HomeSearchState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
