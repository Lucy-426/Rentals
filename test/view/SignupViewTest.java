package view;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import main.app.SignupUseCaseFactory;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SignupViewTest {

    @Test
    void actionPerformed() {
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

        int id = 1;
        String command = "command";
        ActionEvent e = new ActionEvent(new Object(), id, command);

        signupView.actionPerformed(e);
    }

    @Test
    void propertyChange() {
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

        String propertyName = "propertyName";
        PropertyChangeEvent e = new PropertyChangeEvent(new SignupState(), propertyName, new SignupState(), new SignupState());
        signupView.propertyChange(e);
    }
}