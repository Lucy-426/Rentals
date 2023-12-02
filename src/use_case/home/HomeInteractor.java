package use_case.home;

import entity.Property;
import entity.PropertyFactory;
import org.jdesktop.swingx.mapviewer.Waypoint;

import java.util.HashMap;
import java.util.Set;

public class HomeInteractor implements HomeInputBoundary {

    final HomeSearchDataAccessInterface homeDataAccessObject;

    final HomeOutputBoundary homePresenter;

    final PropertyFactory propertyFactory;

    public HomeInteractor(HomeSearchDataAccessInterface homesearchDataAccessInterface,
                          HomeOutputBoundary homeOutputBoundary, PropertyFactory propertyFactory) {
        this.homeDataAccessObject = homesearchDataAccessInterface;
        this.homePresenter = homeOutputBoundary;
        this.propertyFactory = propertyFactory;
    }
    @Override
    public void execute(HomeInputData homeInputData) {

        Property property = propertyFactory.create(homeInputData.getId(), homeInputData.getCity(), homeInputData.getAddress(),
                homeInputData.getNumRooms(), homeInputData.getPriceRange(), homeInputData.getNumBaths(),
                homeInputData.getWalkScore(), homeInputData.getFurnished(), homeInputData.getListingType());
        homeDataAccessObject.save(property);

        homeDataAccessObject.filter();
        HashMap<String, String> displayedProperties = homeDataAccessObject.getFilteredProperties();
        Set<Waypoint> waypoints = homeDataAccessObject.getCoordinates(displayedProperties);
        HomeOutputData homeOutputData = new HomeOutputData(displayedProperties, waypoints);
        homePresenter.prepareSuccessView(homeOutputData);
    }

}
