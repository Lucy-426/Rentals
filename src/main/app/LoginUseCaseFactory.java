package main.app;

import data_access.UserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                   LoginViewModel loginViewModel, ListingViewModel listingViewModel,
                                   UserDataAccessObject userDataAccessObject) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, homeSearchViewModel,
                    loginViewModel, listingViewModel, userDataAccessObject);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                                          LoginViewModel loginViewModel, ListingViewModel listingViewModel,
                                                          UserDataAccessObject userDataAccessObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homeSearchViewModel,
                loginViewModel, listingViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
