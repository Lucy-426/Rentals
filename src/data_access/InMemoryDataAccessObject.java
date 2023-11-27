package data_access;

import entity.Property;
import use_case.home.HomeSearchDataAccessInterface;

import java.util.Map;
import java.util.HashMap;
public class InMemoryDataAccessObject implements HomeSearchDataAccessInterface {
    private final Map<String, Property> properties = new HashMap<>();

    public void save(Property property) {
        properties.put(property.getAddress(), property);
    }

    @Override
    public void filter() {

    }

}
