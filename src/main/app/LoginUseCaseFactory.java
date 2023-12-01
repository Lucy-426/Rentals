package main.app;

import data_access.UserDataAccessObject;
import data_access.UserSignupDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.LoginView;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                   LoginViewModel loginViewModel, SavedViewModel savedViewModel,
                                   UserDataAccessObject userDataAccessObject) {

        try {
            LoginController loginController = createUserLoginUseCase(viewManagerModel, homeSearchViewModel,
                    loginViewModel, savedViewModel, userDataAccessObject);
            return new LoginView(loginController, loginViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createUserLoginUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                                          LoginViewModel loginViewModel, SavedViewModel savedViewModel,
                                                          UserDataAccessObject userDataAccessObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, homeSearchViewModel,
                loginViewModel, savedViewModel);

        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        return new LoginController(loginInteractor);
    }
}
