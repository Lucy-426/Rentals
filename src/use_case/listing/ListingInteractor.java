package use_case.listing;

import entity.Property;
import entity.PropertyFactory;
import use_case.home.HomeSearchDataAccessInterface;

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

        ListingOutputData listingOutputData = new ListingOutputData(property.getID(), property.getCity(), property.getAddress(), property.getNumRooms(),
                property.getPriceRange(), property.getNumBaths(), property.getWalkScore(), property.getFurnished(), property.getListingType());
        listingPresenter.prepareSuccessView(listingOutputData);

    }


}
