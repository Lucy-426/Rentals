package main.app;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import view.HomeSearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Got Room?");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // Displaying interactive map
        JXMapKit jxMapKit = new JXMapKit();
        jxMapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        jxMapKit.setDataProviderCreditShown(true);
        jxMapKit.setZoom(5);
        jxMapKit.setAddressLocationShown(true);

        // Set starting point at UofT
        GeoPosition waypoint1 = new GeoPosition(43.6634425, -79.3964002);
        jxMapKit.getMainMap().setAddressLocation(waypoint1);

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        views.add(jxMapKit, "Map");
        application.setMinimumSize(new Dimension(1000, 600));


        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);


        HomeSearchViewModel homesearchViewModel = new HomeSearchViewModel();

        HomeSearchView homeSearchView = HomeSearchUseCaseFactory.create(viewManagerModel, homesearchViewModel);
        views.add(homeSearchView, homeSearchView.viewName);

        viewManagerModel.setActiveView(homeSearchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}