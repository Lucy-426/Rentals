package main.app;

import data_access.UserDataAccessObject;
import interface_adapter.listing.ListingController;
import interface_adapter.listing.ListingPresenter;
import interface_adapter.listing.ListingViewModel;
import data_access.MapDataAccessObject;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchPresenter;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.CenterMap.CenterMapController;
import interface_adapter.CenterMap.CenterMapPresenter;
import org.apache.commons.logging.Log;
import use_case.CenterMap.*;
import use_case.home.*;
import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInteractor;
import use_case.listing.ListingOutputBoundary;
import view.HomeSearchView;
import view.ListingView;

import javax.swing.*;
import java.io.IOException;


public class HomeSearchUseCaseFactory {
    private HomeSearchUseCaseFactory() {};

    public static HomeSearchView create(PropertyDataAccessObject propertyDataAccessObject, UserDataAccessObject userDataAccessObject,
                                        ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                        ListingViewModel listingViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SavedViewModel savedViewModel) {
        try {
            HomeSearchController homeSearchController = createHomeSearchUseCase(propertyDataAccessObject, viewManagerModel, homeSearchViewModel,
                    signupViewModel, loginViewModel, savedViewModel, listingViewModel);

            ListingController listingController = createListingUseCase(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homeSearchViewModel, listingViewModel);
            CenterMapController centerMapController = createCenterMapUseCase(viewManagerModel, homeSearchViewModel);
            return new HomeSearchView(homeSearchController, listingController, homeSearchViewModel, centerMapController);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open property data file.");
        }
        return null;
    }

    private static HomeSearchController createHomeSearchUseCase(PropertyDataAccessObject propertyDataAccessObject, ViewManagerModel viewManagerModel,
                HomeSearchViewModel homeSearchViewModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SavedViewModel savedViewModel,
                                                                ListingViewModel listingViewModel) throws IOException {

        HomeOutputBoundary homeOutputBoundary = new HomeSearchPresenter(homeSearchViewModel, viewManagerModel, signupViewModel,
                loginViewModel, savedViewModel, listingViewModel);

        PropertyFactory propertyFactory = new PropertyFactory();

        HomeInputBoundary homeInteractor = new HomeInteractor(propertyDataAccessObject, homeOutputBoundary, propertyFactory);

        return new HomeSearchController(homeInteractor, homeSearchViewModel, viewManagerModel);

    }
    public static ListingView createListingView(PropertyDataAccessObject propertyDataAccessObject, UserDataAccessObject userDataAccessObject,
                                                ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {
        ListingController listingController = createListingUseCase(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homeSearchViewModel, listingViewModel);
        return new ListingView(listingController, listingViewModel);

    }

    private static ListingController createListingUseCase(PropertyDataAccessObject propertyDataAccessObject, UserDataAccessObject userDataAccessObject,
                                                          ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel, ListingViewModel listingViewModel) {

        ListingOutputBoundary listingOutputBoundary = new ListingPresenter(viewManagerModel, listingViewModel);

        ListingInputBoundary listingInteractor = new ListingInteractor(propertyDataAccessObject, userDataAccessObject, listingOutputBoundary);

        return new ListingController(listingInteractor, homeSearchViewModel, viewManagerModel);

    }

    private static CenterMapController createCenterMapUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel) throws IOException {
        CenterMapOutputBoundary centerMapOutputBoundary = new CenterMapPresenter(homeSearchViewModel, viewManagerModel);

        MapDataAccessObject centerMapDataAccessObject = new MapDataAccessObject();
        CenterMapInputBoundary centerMapInteractor = new CenterMapInteractor(centerMapDataAccessObject, centerMapOutputBoundary);

        return new CenterMapController(centerMapInteractor);
    }
}
