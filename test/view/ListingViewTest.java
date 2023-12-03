package view;

import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import main.app.HomeSearchUseCaseFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

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
        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);
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
        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);

        JButton mockButton = new JButton();
        mockButton.setName("SampleButton");
        ActionEvent mockEvent = new ActionEvent(mockButton, ActionEvent.ACTION_PERFORMED, "command");

        listingView.actionPerformed(mockEvent);

        assertEquals("SampleButton", mockButton.getName());
    }

    @Test
    void propertyChange() {

    }
}