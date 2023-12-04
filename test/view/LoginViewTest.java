package view;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import main.app.LoginUseCaseFactory;
import org.junit.jupiter.api.Test;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewTest {

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
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, homeSearchViewModel, loginViewModel, listingViewModel, userDataAccessObject);

        int id = 1;
        String button = "button";
        ActionEvent e = new ActionEvent(new Object(), id, button);
        loginView.actionPerformed(e);
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
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, homeSearchViewModel, loginViewModel, listingViewModel, userDataAccessObject);

        String propertyName = "propertyName";
        PropertyChangeEvent e = new PropertyChangeEvent(new LoginState(), propertyName, new LoginState(), new LoginState());

        loginView.propertyChange(e);
    }
}