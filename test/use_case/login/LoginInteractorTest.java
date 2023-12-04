package use_case.login;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.login.LoginPresenter;
import org.junit.jupiter.api.Test;
import use_case.signup.UserSignupDataAccessInterface;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    @Test
    void successTest() throws IOException {
        UserSignupDataAccessInterface users = new UserDataAccessObject("./users.csv", new CommonUserFactory());

        LoginOutputBoundary successPresenter = new LoginOutputBoundary() {
            @Override
            public void prepareSuccessView(LoginOutputData user) {
                assertEquals("Lucy", user.getUsername());
                assertTrue(users.existsByName("Lucy"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Wrong password or user doesn't exist.");
            }

            @Override
            public void displayHome() {

            }
        };

        LoginInputData loginInputData = new LoginInputData("Lucy", "123");
        LoginInteractor loginInteractor = new LoginInteractor(users, successPresenter);
        loginInteractor.execute(loginInputData);
    }

    @Test
    void displayHome() {
    }
}