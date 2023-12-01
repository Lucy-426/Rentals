package data_access;

import entity.Property;
import entity.User;

import java.util.ArrayList;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    String getUserPassword(String identifier);

    ArrayList<Property> getUserProperties(String username);

    void saveUserProperty(String username, Property property);

    void save(User user);
}
