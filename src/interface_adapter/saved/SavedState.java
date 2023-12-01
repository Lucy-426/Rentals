package interface_adapter.saved;

import entity.Property;

import java.util.ArrayList;

public class SavedState {
    private String username = "";
    private ArrayList<Property> savedListings;

    public SavedState(SavedState copy) {
        username = copy.username;
        savedListings = copy.savedListings;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public SavedState() {}

    public String getUsername() {
        return username;
    }

    public ArrayList<Property> getSavedListings() { return savedListings; }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSavedListings(ArrayList<Property> savedListings) {
        this.savedListings = savedListings;
    }

}
