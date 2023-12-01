package interface_adapter.listing;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInputData;

public class ListingController {
    final ListingInputBoundary listingInteractor;

    final HomeSearchViewModel homeSearchViewModel;

    final ViewManagerModel viewManagerModel;


    public ListingController(ListingInputBoundary listingInteractor, HomeSearchViewModel homeSearchViewModel,
                             ViewManagerModel viewManagerModel) {
        this.listingInteractor = listingInteractor;
        this.homeSearchViewModel = homeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void execute(String id){
        ListingInputData listingInputData = new ListingInputData(id);

        listingInteractor.execute(listingInputData);
    }

    public void switchView() {
        HomeSearchState homeSearchState = homeSearchViewModel.getState();
        this.homeSearchViewModel.setState(homeSearchState);
        homeSearchViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
