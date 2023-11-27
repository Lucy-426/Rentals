package interface_adapter.homeSearch;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class HomeSearchViewModel extends ViewModel {
    public final String TITLE_LABEL = "Home Search View";

    public final String SEARCH_BAR_LABEL = "Enter a city, address, or listing ID.";

    public final String[] numRoomStrings = {"all", "1", "2", "3", "4", "5+"};

    public final String[] priceRangeStrings = {"all", "<100000", "100000-300000", "300000-500000", "500000+"};

    public final String[] numBathsStrings = {"all", "1", "2", "3", "4+"};
    public final String[] walkScoreStrings = {"all", "1-3", "3-6", "6-10"};
    public final String[] furnishedStrings = {"all", "furnished", "not furnished"};

    public final String[] listingTypeStrings = {"all", "house", "townhouse", "apartment", "other"};



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
