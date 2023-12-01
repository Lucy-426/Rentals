package main.app;

import data_access.HomeSearchDataAccessInterface;
import data_access.InMemoryDataAccessObject;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchPresenter;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import use_case.home.*;
import view.HomeSearchView;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

//TODO: add exceptions / try/catch
public class HomeSearchUseCaseFactory {
    private HomeSearchUseCaseFactory() {};

    public static HomeSearchView create(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                        SignupViewModel signupViewModel, LoginViewModel loginViewModel, SavedViewModel savedViewModel) {
        try {
            HomeSearchController homeSearchController = createHomeSearchUseCase(viewManagerModel, homeSearchViewModel,
                    signupViewModel, loginViewModel, savedViewModel);
            return new HomeSearchView(homeSearchController, homeSearchViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open property data file.");
        }
        return null;
    }

    private static HomeSearchController createHomeSearchUseCase(ViewManagerModel viewManagerModel, HomeSearchViewModel homeSearchViewModel,
                                                                SignupViewModel signupViewModel, LoginViewModel loginViewModel,
                                                                SavedViewModel savedViewModel) throws IOException {
        HomeSearchDataAccessInterface homeDataAccessObject = new InMemoryDataAccessObject();
        HomeSearchDataAccessInterface propertyDataAccessObject = new PropertyDataAccessObject("./properties.csv", new PropertyFactory());

        HomeOutputBoundary homeOutputBoundary = new HomeSearchPresenter(homeSearchViewModel, viewManagerModel, signupViewModel,
                loginViewModel, savedViewModel);

        PropertyFactory propertyFactory = new PropertyFactory();

        HomeInputBoundary homeInteractor = new HomeInteractor(homeDataAccessObject, homeOutputBoundary, propertyFactory);

        return new HomeSearchController(homeInteractor);

    }
}
