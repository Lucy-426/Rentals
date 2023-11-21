package main.app;

import data_access.HomeSearchDataAccessInterface;
import data_access.InMemoryDataAccessObject;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchPresenter;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import use_case.home.*;
import view.HomeSearchView;

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
