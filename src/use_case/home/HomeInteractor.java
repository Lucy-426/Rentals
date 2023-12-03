package use_case.home;

import entity.Property;
import entity.PropertyFactory;
import interface_adapter.saved.SavedState;

import java.util.HashMap;

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
        HomeOutputData homeOutputData = new HomeOutputData(displayedProperties);
        homePresenter.prepareSuccessView(homeOutputData);
    }

    public void displaySignupView() {
        homePresenter.displaySignupView();
    }

    public void displayLoginView() {
        homePresenter.displayLoginView();
    }

    public void displayProfile(SavedState savedState) { homePresenter.displayProfile(savedState); }

    public void logOut() { homePresenter.logOut(); }
}
