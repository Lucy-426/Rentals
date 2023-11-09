package main.app;

import data_access.InMemoryDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.HomeSearchController;
import interface_adapter.HomeSearchPresenter;
import interface_adapter.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import use_case.home.*;
import view.HomeSearchView;
import view.ViewManager;

//TODO: add exceptions / try/catch
public class HomeSearchUseCaseFactory {
    private HomeSearchUseCaseFactory() {};

    public static HomeSearchView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel) {
        HomeSearchController homeSearchController = createHomeSearchUseCase(viewManagerModel, homeSearchViewModel);
        return new HomeSearchView(homeSearchController, homeSearchViewModel);
    }

    private static HomeSearchController createHomeSearchUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel) {
        HomeSearchDataAccessInterface homeDataAccessObject = new InMemoryDataAccessObject();

        HomeOutputBoundary homeOutputBoundary = new HomeSearchPresenter(homeSearchViewModel, viewManagerModel);

        PropertyFactory propertyFactory = new PropertyFactory();

        HomeInputBoundary homeInteractor = new HomeInteractor(homeDataAccessObject, homeOutputBoundary, propertyFactory);

        return new HomeSearchController(homeInteractor);

    }
}
