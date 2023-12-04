package use_case.listing;

import use_case.signup.UserSignupDataAccessInterface;
import entity.Property;
import use_case.home.HomeSearchDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class ListingInteractor implements ListingInputBoundary{

    final HomeSearchDataAccessInterface homeSearchDataAccessObject;
    final UserSignupDataAccessInterface userSignupDataAccessInterface;
    final ListingOutputBoundary listingPresenter;

    public ListingInteractor(HomeSearchDataAccessInterface homeSearchDataAccessInterface,
                             UserSignupDataAccessInterface userSignupDataAccessInterface,
                             ListingOutputBoundary listingOutputBoundary) {
        this.homeSearchDataAccessObject = homeSearchDataAccessInterface;
        this.userSignupDataAccessInterface = userSignupDataAccessInterface;
        this.listingPresenter = listingOutputBoundary;
    }


    @Override
    public void execute(ListingInputData listingInputData) {

        Property property = homeSearchDataAccessObject.getProperty(listingInputData.getId());

        homeSearchDataAccessObject.makeRecommendations(property);
        HashMap<String, String> recommendations = homeSearchDataAccessObject.getRecommendedProperties();

        ListingOutputData listingOutputData = new ListingOutputData(property.getID(), property.getCity(), property.getAddress(), property.getNumRooms(),
                property.getPriceRange(), property.getNumBaths(), property.getWalkScore(), property.getFurnished(), property.getListingType(),
                recommendations);
        listingPresenter.prepareSuccessView(listingOutputData);

    }

    public void saveListing(ListingInputData listingInputData) {
        Property property = homeSearchDataAccessObject.getProperty(listingInputData.getId());

        String msg;
        Map<String, Property> properties = userSignupDataAccessInterface.getUserProperties(listingInputData.getUsername());
        if (properties!= null && !properties.isEmpty() && properties.containsKey(listingInputData.getId())) {
            msg = "This listing has already been saved.";
        } else {
            userSignupDataAccessInterface.saveUserProperty(listingInputData.getUsername(), property);
            msg = "This listing has been saved.";
        }

        // Show pop up that listing has been saved.
        listingPresenter.listingSaved(msg);
    }


}
