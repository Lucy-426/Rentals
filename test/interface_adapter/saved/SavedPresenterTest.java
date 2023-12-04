package interface_adapter.saved;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import org.junit.jupiter.api.Test;
import use_case.saved.SavedOutputData;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class SavedPresenterTest {

    @Test
    void prepareSuccessView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);

        SavedOutputData savedOutputData = new SavedOutputData("testId", "testCity", "testAddress", "testRooms",
                "testPrice", "testBaths", "testScore", "testFurnished",
                "testType", new HashMap<>());
        savedPresenter.prepareSuccessView(savedOutputData);
    }

    @Test
    void prepareFailView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        savedPresenter.prepareFailView("error");
    }

    @Test
    void displayHome() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        savedPresenter.displayHome();
    }

    @Test
    void logOut() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ListingViewModel listingViewModel = new ListingViewModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        SavedPresenter savedPresenter = new SavedPresenter(viewManagerModel, listingViewModel, homeSearchViewModel);
        savedPresenter.logOut();
    }
}