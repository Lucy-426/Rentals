package interface_adapter;

import use_case.home.HomeInputBoundary;
import use_case.home.HomeInputData;

public class HomeSearchController {
    final HomeInputBoundary homeInteractor;

    public HomeSearchController(HomeInputBoundary homeInteractor) {
        this.homeInteractor = homeInteractor;
    }

    public void execute(String address, String rentalType, String contact) {
        HomeInputData homeInputData = new HomeInputData(address, rentalType, contact);

        homeInteractor.execute(homeInputData);
    }
}
