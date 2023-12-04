package interface_adapter.saved;

import data_access.PropertyDataAccessObject;
import data_access.UserDataAccessObject;
import entity.CommonUserFactory;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import org.junit.jupiter.api.Test;
import use_case.saved.SavedInteractor;
import view.SavedView;

import java.io.IOException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavedViewModelTest {

    @Test
    void firePropertyChanged() {
        SavedViewModel savedViewModel = new SavedViewModel();
        savedViewModel.firePropertyChanged();
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

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        SavedInteractor savedInteractor = new SavedInteractor(propertyDataAccessObject, savedPresenter);
        SavedController savedController = new SavedController(savedInteractor);
        SavedViewModel savedViewModel = new SavedViewModel();
        SavedView savedView = new SavedView(savedController, savedViewModel, userDataAccessObject);
        savedViewModel.addPropertyChangeListener(savedView);
    }

    @Test
    void setState() {
        SavedViewModel savedViewModel = new SavedViewModel();
        SavedState state = new SavedState();
        savedViewModel.setState(state);
        assertEquals(state, savedViewModel.getState());
    }

    @Test
    void getState() {}
}