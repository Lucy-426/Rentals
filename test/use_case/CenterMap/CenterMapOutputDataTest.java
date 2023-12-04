package use_case.CenterMap;

import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterMapOutputDataTest {

    @Test
    void getGeoPosition() {
        GeoPosition actual = new GeoPosition(43.6780371, -79.4094439);
        CenterMapOutputData centerMapOutputData = new CenterMapOutputData(actual, false);
        assertEquals(centerMapOutputData.getGeoPosition(), actual);
    }
}