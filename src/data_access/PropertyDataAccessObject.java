package data_access;

import entity.Property;
import entity.PropertyFactory;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class PropertyDataAccessObject {
    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, Property> properties = new HashMap<>();

    private PropertyFactory propertyFactory;

    public PropertyDataAccessObject() {

    }
}
