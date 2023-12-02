package use_case.home;

import entity.Property;

import java.util.HashMap;

public interface HomeSearchDataAccessInterface {

    void save(Property property);
    void filter();

    HashMap<String, String> getFilteredProperties();

    Property getProperty(String id);

}
