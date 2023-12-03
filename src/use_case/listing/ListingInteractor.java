package use_case.listing;

import entity.Property;
import use_case.home.HomeSearchDataAccessInterface;

import java.util.HashMap;

public class ListingInteractor implements ListingInputBoundary{

    final HomeSearchDataAccessInterface homeSearchDataAccessObject;
    final ListingOutputBoundary listingPresenter;

    public ListingInteractor(HomeSearchDataAccessInterface homeSearchDataAccessInterface, ListingOutputBoundary listingOutputBoundary) {
        this.homeSearchDataAccessObject = homeSearchDataAccessInterface;
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


}
