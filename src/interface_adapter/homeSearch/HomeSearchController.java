package interface_adapter.homeSearch;

import interface_adapter.saved.SavedState;
import interface_adapter.ViewManagerModel;
import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeSearchController {
    final HomeInputBoundary homeInteractor;

    final HomeSearchViewModel homeSearchViewModel;

    final ViewManagerModel viewManagerModel;

    public HomeSearchController(HomeInputBoundary homeInteractor, HomeSearchViewModel homeSearchViewModel,
                                ViewManagerModel viewManagerModel) {
        this.homeInteractor = homeInteractor;
        this.homeSearchViewModel = homeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void execute(String id, String city, String address, String numRooms, String priceRange,
                        String numBaths, String walkScore, String furnished, String listingType) {
        HomeInputData homeInputData = new HomeInputData(id, city, address, numRooms, priceRange, numBaths, walkScore, furnished, listingType);

        homeInteractor.execute(homeInputData);
    }

    public void displaySignupView() {
        homeInteractor.displaySignupView();
    }

    public void displayLoginView() {
        homeInteractor.displayLoginView();
    }

    public void displayProfile(SavedState savedState) { homeInteractor.displayProfile(savedState); }

    public void logOut() { homeInteractor.logOut(); }

}
