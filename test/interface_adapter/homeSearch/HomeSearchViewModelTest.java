package interface_adapter.homeSearch;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.Property;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.listing.ListingController;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedViewModel;
import interface_adapter.signup.SignupViewModel;
import main.app.HomeSearchUseCaseFactory;
import org.junit.jupiter.api.Test;
import use_case.home.HomeInteractor;
import use_case.listing.ListingInteractor;
import view.HomeSearchView;
import view.ListingView;

import java.beans.PropertyChangeListener;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class HomeSearchViewModelTest {

    @Test
    void firePropertyChanged() {
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        homeSearchViewModel.firePropertyChanged();
    }

    @Test
    void addPropertyChangeListener() {
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
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        HomeSearchView view = HomeSearchUseCaseFactory.create(propertyDataAccessObject, userDataAccessObject, new ViewManagerModel(),
                homeSearchViewModel, new ListingViewModel(), new SignupViewModel(), new LoginViewModel(), new SavedViewModel());
        homeSearchViewModel.addPropertyChangeListener(view);
    }

    @Test
    void setState() {
        HomeSearchState homeSearchState = new HomeSearchState();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        homeSearchViewModel.setState(homeSearchState);
    }

    @Test
    void getState() {
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        HomeSearchState state = homeSearchViewModel.getState();
    }
}