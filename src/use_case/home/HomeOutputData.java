package use_case.home;

import org.jdesktop.swingx.mapviewer.Waypoint;

import java.util.HashMap;
import java.util.Set;

public class HomeOutputData {
    private final HashMap<String, String> displayedProperties;

    private final Set<Waypoint> waypoints;

    public HomeOutputData(HashMap<String, String> displayedProperties, Set<Waypoint> waypoints) {
        this.displayedProperties = displayedProperties;
        this.waypoints = waypoints;
    }

    public HashMap<String, String> getDisplayedProperties() {
        return displayedProperties;
    }

    public Set<Waypoint> getWaypoints() {
        return waypoints;
    }
}
