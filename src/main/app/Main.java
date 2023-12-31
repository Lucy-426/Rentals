package main.app;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;

import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.listing.ListingViewModel;
import view.*;

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

        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HomeSearchViewModel homesearchViewModel = new HomeSearchViewModel();
        SignupViewModel signupViewModel = new SignupViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        SavedViewModel savedViewModel = new SavedViewModel();

        PropertyDataAccessObject propertyDataAccessObject;
        try {
            propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HomeSearchView homeSearchView = HomeSearchUseCaseFactory.create(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel,
                signupViewModel, loginViewModel, savedViewModel);
        views.add(homeSearchView, homeSearchView.viewName);

        ListingView listingView = HomeSearchUseCaseFactory.createListingView(propertyDataAccessObject, userDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel);
        views.add(listingView, listingView.viewName);

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, homesearchViewModel, loginViewModel, signupViewModel,
                userDataAccessObject);
        views.add(signupView, signupView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, homesearchViewModel, loginViewModel, listingViewModel,
                userDataAccessObject);
        views.add(loginView, loginView.viewName);

        SavedView savedView = ProfileUseCaseFactory.create(userDataAccessObject, propertyDataAccessObject, viewManagerModel, homesearchViewModel, listingViewModel, savedViewModel);
        views.add(savedView, savedView.viewName);


        viewManagerModel.setActiveView(homeSearchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setMinimumSize(new Dimension(1000, 600));
        application.setVisible(true);
    }
}