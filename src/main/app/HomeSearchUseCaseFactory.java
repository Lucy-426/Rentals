package main.app;

import interface_adapter.listing.ListingController;
import interface_adapter.listing.ListingPresenter;
import interface_adapter.listing.ListingViewModel;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchPresenter;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import use_case.home.*;
import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInteractor;
import use_case.listing.ListingOutputBoundary;
import view.HomeSearchView;
import view.ListingView;

import javax.swing.*;
import java.io.IOException;

//TODO: add exceptions / try/catch?
public class HomeSearchUseCaseFactory {
    private HomeSearchUseCaseFactory() {};

    public static HomeSearchView create(PropertyDataAccessObject propertyDataAccessObject, ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        try {
            HomeSearchController homeSearchController = createHomeSearchUseCase(propertyDataAccessObject, viewManagerModel, homeSearchViewModel);
            ListingController listingController = createListingUseCase(propertyDataAccessObject, viewManagerModel, homeSearchViewModel, listingViewModel);
            return new HomeSearchView(homeSearchController, listingController, homeSearchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open property data file.");
        }
        return null;
    }

    private static HomeSearchController createHomeSearchUseCase(PropertyDataAccessObject propertyDataAccessObject, ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel) throws IOException {

        HomeOutputBoundary homeOutputBoundary = new HomeSearchPresenter(homeSearchViewModel, viewManagerModel);

        PropertyFactory propertyFactory = new PropertyFactory();

        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeOutputBoundary, propertyFactory);

        return new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);

    }
    public static ListingView createListingView(PropertyDataAccessObject propertyDataAccessObject, ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        ListingController listingController = createListingUseCase(propertyDataAccessObject, viewManagerModel, homeSearchViewModel, listingViewModel);
        return new ListingView(listingController, listingViewModel);

    }

    private static ListingController createListingUseCase(PropertyDataAccessObject propertyDataAccessObject, ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {

        ListingOutputBoundary listingOutputBoundary = new ListingPresenter(viewManagerModel, listingViewModel);

        ListingInputBoundary listingInteractor = new ListingInteractor(propertyDataAccessObject, listingOutputBoundary);

        return new ListingController(listingInteractor, homeSearchViewModel, viewManagerModel);
    }
}
