package view;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.saved.SavedController;
import interface_adapter.saved.SavedPresenter;
import interface_adapter.saved.SavedViewModel;
import org.junit.jupiter.api.Test;
import use_case.saved.SavedInteractor;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SavedViewTest {

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

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        SavedInteractor savedInteractor = new SavedInteractor(propertyDataAccessObject, savedPresenter);
        SavedController savedController = new SavedController(savedInteractor);
        SavedViewModel savedViewModel = new SavedViewModel();
        SavedView savedView = new SavedView(savedController, savedViewModel, userDataAccessObject);

        int id = 1;
        String command = "command";
        ActionEvent e = new ActionEvent(new Object(), id, command);

        savedView.actionPerformed(e);

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

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        SavedInteractor savedInteractor = new SavedInteractor(propertyDataAccessObject, savedPresenter);
        SavedController savedController = new SavedController(savedInteractor);
        SavedViewModel savedViewModel = new SavedViewModel();
        SavedView savedView = new SavedView(savedController, savedViewModel, userDataAccessObject);

        String propertyName = "propertyName";
        PropertyChangeEvent e = new PropertyChangeEvent(new Object(), propertyName, new Object(), new Object());

        savedView.propertyChange(e);
    }
}