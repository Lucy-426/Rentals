package use_case.home;

import entity.Property;
import entity.PropertyFactory;

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
        // TODO: change input/output data so it's not hard coded
        Property property = propertyFactory.create("address", "apartment", "contact");
        homeDataAccessObject.save(property);

        HomeOutputData homeOutputData = new HomeOutputData("address1", "apartment1", "contact1");
        homePresenter.prepareSuccessView(homeOutputData);
    }
}
