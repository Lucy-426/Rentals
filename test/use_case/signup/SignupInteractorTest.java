package use_case.signup;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupInteractorTest {

    @Test
    void successTest() throws IOException {
        UserSignupDataAccessInterface users = new UserDataAccessObject("./user.csv", new CommonUserFactory());

        SignupOutputBoundary successPresenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals("Lucy", user.getUsername());
                assertNotNull(user.getCreationTime());
                assertTrue(users.existsByName("Lucy"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void displayHome() {

            }
        };

        SignupInputData inputData = new SignupInputData("Lucy", "123", "123");
        SignupInputBoundary interactor = new SignupInteractor(users, successPresenter, new CommonUserFactory());

        interactor.execute(inputData);
    }

    @Test
    void displayHome() {
    }
}