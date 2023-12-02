package main.app;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;

import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.listing.ListingViewModel;
import view.HomeSearchView;
import view.ListingView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Got Room?");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        HomeSearchViewModel homesearchViewModel = new HomeSearchViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();

        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        HomeSearchView homeSearchView = HomeSearchUseCaseFactory.create(propertyDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);
        views.add(homeSearchView, homeSearchView.viewName);

        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);
        views.add(listingView, listingView.viewName);

        viewManagerModel.setActiveView(homeSearchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setMinimumSize(new Dimension(1000, 600));
        application.setVisible(true);
    }
}