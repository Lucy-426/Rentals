package use_case.home;

import entity.Property;
import org.jdesktop.swingx.mapviewer.Waypoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface HomeSearchDataAccessInterface {

    void save(Property property);

    void filter();

    HashMap<String, String> getFilteredProperties();

    Property getProperty(String id);

    /*Converting set of properties into set of its corresponding waypoints
    with the latitude/longitude coordinates*/
    Set<Waypoint> getCoordinates(HashMap<String, String> properties);

    double getLat(String id);

    double getLong(String id);

    void makeRecommendations(Property property);

    HashMap<String, String> getRecommendedProperties();
}
