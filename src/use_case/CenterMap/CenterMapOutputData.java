package use_case.CenterMap;

import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

public class CenterMapOutputData {

    private final GeoPosition geoPosition;

    private boolean useCaseFailed;

    public CenterMapOutputData(GeoPosition geoPosition, boolean useCaseFailed) {
        this.geoPosition = geoPosition;
        this.useCaseFailed = useCaseFailed;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }
}
