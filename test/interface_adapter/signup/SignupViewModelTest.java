package interface_adapter.signup;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.login.LoginViewModel;
import main.app.SignupUseCaseFactory;
import org.junit.jupiter.api.Test;
import view.SignupView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupViewModelTest {

    @Test
    void setState() {
        SignupViewModel signupViewModel = new SignupViewModel();
        signupViewModel.setState(new SignupState());

    }

    @Test
    void firePropertyChanged() {
        SignupViewModel signupViewModel = new SignupViewModel();
        signupViewModel.firePropertyChanged();

    }

    @Test
    void addPropertyChangeListener() {
        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, homeSearchViewModel, loginViewModel,
                signupViewModel, userDataAccessObject);

        signupViewModel.addPropertyChangeListener(signupView);

    }

    @Test
    void getState() {
        SignupViewModel signupViewModel = new SignupViewModel();
        SignupState signupState = new SignupState();
        signupViewModel.setState(signupState);
        assertEquals(signupState, signupViewModel.getState());
    }
}