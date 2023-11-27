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
        // TODO: edit so it's not hard coded

        ListingOutputData listingOutputData = new ListingOutputData("123456", "Apartment","2", "300000", "1", "3", "furnished",
                "apartment");
        listingPresenter.prepareSuccessView(listingOutputData);

    }


}
