package view;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingState;
import interface_adapter.listing.ListingViewModel;
import main.app.HomeSearchUseCaseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

class ListingViewTest {
    private ListingView listingView;
    @BeforeEach
    void init() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        HomeSearchViewModel homesearchViewModel = new HomeSearchViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();

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

        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);
        assertEquals("Listing", listingView.viewName);

    }
    @Test
    void actionPerformed() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        HomeSearchViewModel homesearchViewModel = new HomeSearchViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();

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

        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);

        JButton mockButton = new JButton();
        mockButton.setName("SampleButton");
        ActionEvent mockEvent = new ActionEvent(mockButton, ActionEvent.ACTION_PERFORMED, "command");

        listingView.actionPerformed(mockEvent);

        assertEquals("SampleButton", mockButton.getName());
        listingViewModel.addPropertyChangeListener(listingView);
    }

    @Test
    void propertyChange() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        HomeSearchViewModel homesearchViewModel = new HomeSearchViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();

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

        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);
        PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "view", null, null);
        HashMap<String, String> reccTest = new HashMap<>();
        reccTest.put("recProp1", "test1");
        reccTest.put("recProp2", "test2");
        reccTest.put("recProp3", "test3");
        ListingState state = listingViewModel.getState();
        state.setRecommendations(reccTest);
        listingView.propertyChange(propertyChangeEvent);
    }
}