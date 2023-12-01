package use_case.login;

import entity.Property;

import java.util.ArrayList;

public class LoginOutputData {
    private final String username;

    private final ArrayList<Property> savedListings;

    public LoginOutputData(String username, ArrayList<Property> savedListings) {
        this.username = username;
        this.savedListings = savedListings;
    }

    public String getUsername() { return this.username; }

    public ArrayList<Property> getSavedListings() {
        return savedListings;
    }
}
