package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeSearchViewModel extends ViewModel{
    public final String TITLE_LABEL = "Sign Up View";

    public final String SEARCH_BAR_LABEL = "Enter a city, address, or listing ID.";

    public final String[] numRoomStrings = {"all", "1", "2", "3", "4", "5+"};

    public final String[] priceRangeStrings = {"all", "<1000", "1000-1500", "1500-2000", "2000+"};

    public final String[] numBathsStrings = {"all", "1", "2", "3", "4+"};

    public HomeSearchViewModel() {
        super("Search");
    }

    private HomeSearchState state = new HomeSearchState();

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(HomeSearchState state) {
        this.state = state;
    }

    public HomeSearchState getState() {
        return state;
    }
}
