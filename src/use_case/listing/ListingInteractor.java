package use_case.listing;

import entity.PropertyFactory;
import use_case.home.HomeOutputBoundary;

public class ListingInteractor implements ListingInputBoundary{
    final ListingOutputBoundary listingPresenter;
    final PropertyFactory propertyFactory;

    public ListingInteractor(ListingOutputBoundary listingOutputBoundary, PropertyFactory propertyFactory) {
        this.listingPresenter = listingOutputBoundary;
        this.propertyFactory = propertyFactory;
    }


    @Override
    public void execute(ListingInputData listingInputData) {


        ListingOutputData listingOutputData = new ListingOutputData("22", "New York", "Apartment",60, 350, 8, 3, 2,
                300, true, false, "John Smith 4373294732");
        listingPresenter.prepareSuccessView(listingOutputData);

    }


}
