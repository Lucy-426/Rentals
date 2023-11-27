package use_case.login;

import use_case.signup.SignupInputData;

public interface LoginInputBoundary {
    void execute(LoginInputData loginInputData);

    void displayHome();
}
