package view;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import main.app.HomeSearchUseCaseFactory;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HomeSearchViewTest {

    @Test
    void actionPerformed() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        HomeSearchView view = HomeSearchUseCaseFactory.create(propertyDataAccessObject, userDataAccessObject, new ViewManagerModel(),
                homeSearchViewModel, new ListingViewModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel());

        int id = 1;
        String command = "command";
        ActionEvent e = new ActionEvent(new Object(), id, command);
        view.actionPerformed(e);
    }

    @Test
    void propertyChange() {
        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        HomeSearchView view = HomeSearchUseCaseFactory.create(propertyDataAccessObject, userDataAccessObject, new ViewManagerModel(),
                homeSearchViewModel, new ListingViewModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel());

        String propertyName = "propertyName";
        PropertyChangeEvent e = new PropertyChangeEvent(new Object(), propertyName, new Object(), new Object());
        view.propertyChange(e);
    }
}