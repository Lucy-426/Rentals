package main.app;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.*;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                    LoginViewModel loginViewModel, SignupViewModel signupViewModel,
                                    UserDataAccessObject userDataAccessObject) {

        try {
            SignupController signupController = createUserSignupUseCase(viewManagerModel, homeSearchViewModel,
                    signupViewModel, loginViewModel, userDataAccessObject);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignupController createUserSignupUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                                            SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                            UserDataAccessObject userDataAccessObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, homeSearchViewModel,
                signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }
}
