package main.app;

import data_access.ListingDataAccessObject;
import entity.ListingFactory;
import interface_adapter.listing.ListingController;
import interface_adapter.listing.ListingPresenter;
import interface_adapter.listing.ListingViewModel;
import use_case.home.HomeSearchDataAccessInterface;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchPresenter;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import use_case.home.*;
import use_case.listing.ListingDataAccessInterface;
import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInteractor;
import use_case.listing.ListingOutputBoundary;
import view.HomeSearchView;
import view.ListingView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

//TODO: add exceptions / try/catch?
public class HomeSearchUseCaseFactory {
    private HomeSearchUseCaseFactory() {};

    public static HomeSearchView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        try {
            HomeSearchController homeSearchController = createHomeSearchUseCase(viewManagerModel, homeSearchViewModel,listingViewModel);
            ListingController listingController = createListingUseCase(viewManagerModel, homeSearchViewModel, listingViewModel);
            return new HomeSearchView(homeSearchController, listingController, homeSearchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open property data file.");
        }
        return null;
    }

    private static HomeSearchController createHomeSearchUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) throws IOException {
        HomeSearchDataAccessInterface propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());

        HomeOutputBoundary homeOutputBoundary = new HomeSearchPresenter(homeSearchViewModel, listingViewModel, viewManagerModel);

        PropertyFactory propertyFactory = new PropertyFactory();

        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeOutputBoundary, propertyFactory);

        return new HomeSearchController(homeInteractor);

    }
    public static ListingView createListingView(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        ListingController listingController = createListingUseCase(viewManagerModel, homeSearchViewModel, listingViewModel);
        return new ListingView(listingController, listingViewModel);

    }

    private static ListingController createListingUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        ListingDataAccessInterface listingDataAccessObject = new ListingDataAccessObject(new ListingFactory());

        ListingOutputBoundary listingOutputBoundary = new ListingPresenter(viewManagerModel, listingViewModel);

        ListingFactory listingFactory = new ListingFactory();

        ListingInputBoundary listingInteractor = new ListingInteractor(listingDataAccessObject, listingOutputBoundary, listingFactory);

        return new ListingController(listingInteractor, homeSearchViewModel, viewManagerModel);
    }
}
