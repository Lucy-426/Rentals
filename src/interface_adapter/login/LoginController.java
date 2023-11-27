package interface_adapter.login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

public class LoginController {

    final LoginInputBoundary loginInteractor;
    public LoginController(LoginInputBoundary loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(username, password);

        loginInteractor.execute(loginInputData);
    }

    public void displayHome() {
        loginInteractor.displayHome();
    }
}
