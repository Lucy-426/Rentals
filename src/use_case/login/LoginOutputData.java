package use_case.login;

import entity.Property;

import java.util.ArrayList;
import java.util.Map;

public class LoginOutputData {
    private final String username;

    private final Map<String, Property> savedListings;

    public LoginOutputData(String username, Map<String, Property> savedListings) {
        this.username = username;
        this.savedListings = savedListings;
    }

    public String getUsername() { return this.username; }

    public Map<String, Property> getSavedListings() {
        return savedListings;
    }
}
