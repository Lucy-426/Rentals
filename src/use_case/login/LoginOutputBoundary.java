package use_case.login;

import use_case.signup.SignupOutputData;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData user);

    void prepareFailView(String error);

    void displayHome();
}
