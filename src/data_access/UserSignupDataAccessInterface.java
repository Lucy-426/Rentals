package data_access;

import entity.Property;
import entity.User;

import java.util.ArrayList;
import java.util.Map;

public interface UserSignupDataAccessInterface {
    boolean existsByName(String identifier);

    String getUserPassword(String identifier);

    Map<String, Property> getUserProperties(String username);

    void saveUserProperty(String username, Property property);

    void save(User user);
}
