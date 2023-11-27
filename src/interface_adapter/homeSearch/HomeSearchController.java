package interface_adapter.homeSearch;

import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeSearchController {
    final HomeInputBoundary homeInteractor;

    public HomeSearchController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void execute(String id, String city, String address, String numRooms, String priceRange,
                        String numBaths, String walkScore, String furnished, String listingType) {
        HomeInputData homeInputData = new HomeInputData(id, city, address, numRooms, priceRange, numBaths, walkScore, furnished, listingType);

        homeInteractor.execute(homeInputData);
    }

}
