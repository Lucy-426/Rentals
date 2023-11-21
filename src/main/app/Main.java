package main.app;

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

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);


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
