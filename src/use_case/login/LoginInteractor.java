package use_case.login;

import data_access.UserSignupDataAccessInterface;
import entity.User;
import use_case.signup.SignupOutputData;

import java.time.LocalDateTime;
import java.util.Objects;

public class LoginInteractor implements LoginInputBoundary {
    final UserSignupDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    public LoginInteractor(UserSignupDataAccessInterface userSignupDataAccessInterface,
                            LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        System.out.println(userDataAccessObject.existsByName(loginInputData.getUsername()));
        System.out.println("user: " + loginInputData.getUsername());
        if (userDataAccessObject.existsByName(loginInputData.getUsername())) {
            if (Objects.equals(userDataAccessObject.getUserPassword(loginInputData.getUsername()), loginInputData.getPassword())) {
                LoginOutputData loginOutputData = new LoginOutputData(loginInputData.getUsername());
                loginPresenter.prepareSuccessView(loginOutputData);
            } else {
                loginPresenter.prepareFailView("You have entered an incorrect password.");
            }
        } else {
            loginPresenter.prepareFailView("Incorrect username or user doesn't exist");
        }
    }

    public void displayHome() {
        loginPresenter.displayHome();
    }
}
