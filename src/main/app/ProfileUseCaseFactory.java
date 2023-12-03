package main.app;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedController;
import interface_adapter.saved.SavedPresenter;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInteractor;
import use_case.saved.SavedOutputBoundary;
import view.LoginView;
import view.SavedView;

import javax.swing.*;
import java.io.IOException;

public class ProfileUseCaseFactory {

    /** Prevent instantiation. */
    private ProfileUseCaseFactory() {}

    public static SavedView create(UserDataAccessObject userDataAccessObject, PropertyDataAccessObject propertyDataAccessObject,
                                   ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                   ListingViewModel listingViewModel, SavedViewModel savedViewModel) {

        try {
            SavedController savedController = createUserProfileUseCase(propertyDataAccessObject, viewManagerModel, homeSearchViewModel,
                    listingViewModel);
            return new SavedView(savedController, savedViewModel, userDataAccessObject);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data profile.");
        }

        return null;
    }

    private static SavedController createUserProfileUseCase(PropertyDataAccessObject propertyDataAccessObject,
                                                            ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                                            ListingViewModel listingViewModel) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        SavedOutputBoundary savedOutputBoundary = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);

        SavedInputBoundary savedInteractor = new SavedInteractor(propertyDataAccessObject, savedOutputBoundary);

        return new SavedController(savedInteractor);
    }
}
