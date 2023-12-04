package use_case.saved;

import entity.Property;
import use_case.home.HomeSearchDataAccessInterface;
import use_case.listing.ListingOutputData;

import java.util.HashMap;

public class SavedInteractor implements SavedInputBoundary {
    final HomeSearchDataAccessInterface homeSearchDataAccessObject;
    final SavedOutputBoundary savedPresenter;

    public SavedInteractor(HomeSearchDataAccessInterface homeSearchDataAccessInterface,
                           SavedOutputBoundary savedOutputBoundary) {
        this.homeSearchDataAccessObject = homeSearchDataAccessInterface;
        this.savedPresenter = savedOutputBoundary;
    }


    @Override
    public void execute(SavedInputData savedInputData) {
        Property property = homeSearchDataAccessObject.getProperty(savedInputData.getId());

        homeSearchDataAccessObject.makeRecommendations(property);
        HashMap<String, String> recommendations = homeSearchDataAccessObject.getRecommendedProperties();

        SavedOutputData savedOutputData = new SavedOutputData(property.getID(), property.getCity(), property.getAddress(), property.getNumRooms(),
                property.getPriceRange(), property.getNumBaths(), property.getWalkScore(), property.getFurnished(), property.getListingType(),
                recommendations);
        savedPresenter.prepareSuccessView(savedOutputData);
    }

    public void displayHome() { savedPresenter.displayHome(); }

    public void logOut() { savedPresenter.logOut(); }


}
