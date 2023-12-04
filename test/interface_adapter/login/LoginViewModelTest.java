package interface_adapter.login;

import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import main.app.LoginUseCaseFactory;
import org.junit.jupiter.api.Test;
import view.LoginView;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginViewModelTest {

    @Test
    void setState() {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.setState(new LoginState());
    }

    @Test
    void firePropertyChanged() {
        LoginViewModel loginViewModel = new LoginViewModel();
        loginViewModel.firePropertyChanged();
    }

    @Test
    void addPropertyChangeListener() {
        UserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new UserDataAccessObject("./users.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, homeSearchViewModel, loginViewModel, listingViewModel, userDataAccessObject);
        loginViewModel.addPropertyChangeListener(loginView);

    }

    @Test
    void getState() {
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginState state = loginViewModel.getState();
        state.setPassword("password");
        assertEquals(state.getPassword(), "password");
    }
}