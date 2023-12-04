package interface_adapter.saved;

import entity.Property;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SavedState {
    private String username = "";
    private Map<String, Property> savedListings;

    public SavedState(SavedState copy) {
        username = copy.username;
        savedListings = copy.savedListings;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SavedState() {}

    public String getUsername() {
        return username;
    }

    public Map<String, Property> getSavedListings() { return savedListings; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSavedListings(Map<String, Property> savedListings) {
        this.savedListings = savedListings;
    }

}
