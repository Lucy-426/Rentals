package main.app;

import data_access.InMemoryDataAccessObject;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.HomeSearchController;
import interface_adapter.HomeSearchPresenter;
import interface_adapter.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import use_case.home.*;
import view.HomeSearchView;
import view.ViewManager;

import javax.swing.*;
import java.io.IOException;

//TODO: add exceptions / try/catch
public class HomeSearchUseCaseFactory {
    private HomeSearchUseCaseFactory() {};

    public static HomeSearchView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel) {
        try {
            HomeSearchController homeSearchController = createHomeSearchUseCase(viewManagerModel, homeSearchViewModel);
            return new HomeSearchView(homeSearchController, homeSearchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open property data file.");
        }
        return null;
    }

    private static HomeSearchController createHomeSearchUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel) throws IOException {
        HomeSearchDataAccessInterface homeDataAccessObject = new InMemoryDataAccessObject();
        HomeSearchDataAccessInterface propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());

        HomeOutputBoundary homeOutputBoundary = new HomeSearchPresenter(homeSearchViewModel, viewManagerModel);

        PropertyFactory propertyFactory = new PropertyFactory();

        HomeInputBoundary homeInteractor = new HomeInteractor(homeDataAccessObject, homeOutputBoundary, propertyFactory);

        return new HomeSearchController(homeInteractor);

    }
}
