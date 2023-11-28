package use_case.CenterMap;

import data_access.MapDataAccessObject;
import org.jdesktop.swingx.mapviewer.GeoPosition;

public class CenterMapInteractor implements CenterMapInputBoundary {

    final MapDataAccessObject mapDataAccessObject;

    final CenterMapOutputBoundary centerMapPresenter;

    public CenterMapInteractor(MapDataAccessObject mapDataAccessObject, CenterMapOutputBoundary centerMapPresenter) {
        this.mapDataAccessObject = mapDataAccessObject;
        this.centerMapPresenter = centerMapPresenter;
    }

    @Override
    public void execute(CenterMapInputData centerMapInputData) {
        // Validate address
        // Get the lat/lng coordinates of the address
        // Create a Waypoint object
        // Pass in that object as output data
        if (!mapDataAccessObject.exists(centerMapInputData.getAddress())) {
            centerMapPresenter.prepareFailView("Invalid address.");
        } else {
            String address = centerMapInputData.getAddress();
            double lat = mapDataAccessObject.getLat(address);
            double lng = mapDataAccessObject.getLong(address);

            // Possibly create a geoposition factory?
            GeoPosition geoPosition = new GeoPosition(lat, lng);
            CenterMapOutputData centerMapOutputData = new CenterMapOutputData(geoPosition, false);
            centerMapPresenter.prepareSuccessView(centerMapOutputData);
        }
    }
}
