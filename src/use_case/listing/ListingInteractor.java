package use_case.listing;

import entity.Listing;
import entity.ListingFactory;
import entity.PropertyFactory;
import use_case.home.HomeOutputBoundary;

public class ListingInteractor implements ListingInputBoundary{

    final ListingDataAccessInterface listingDataAccessObject;
    final ListingOutputBoundary listingPresenter;
    final ListingFactory listingFactory;

    public ListingInteractor(ListingDataAccessInterface listingDataAccessInterface, ListingOutputBoundary listingOutputBoundary, ListingFactory listingFactory) {
        this.listingDataAccessObject = listingDataAccessInterface;
        this.listingPresenter = listingOutputBoundary;
        this.listingFactory = listingFactory;
    }


    @Override
    public void execute(ListingInputData listingInputData) {


        ListingOutputData listingOutputData = new ListingOutputData("Apartment",60, 350, 8, 3, 2,
                300, true, false, "John Smith 4373294732");
        listingPresenter.prepareSuccessView(listingOutputData);

    }


}
