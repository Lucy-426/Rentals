package interface_adapter.CenterMap;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import use_case.CenterMap.CenterMapOutputBoundary;
import use_case.CenterMap.CenterMapOutputData;

public class CenterMapPresenter implements CenterMapOutputBoundary {

    private final HomeSearchViewModel homeSearchViewModel;

    private ViewManagerModel viewManagerModel;

    public CenterMapPresenter(HomeSearchViewModel homeSearchViewModel, ViewManagerModel viewManagerModel) {
        this.homeSearchViewModel = homeSearchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CenterMapOutputData centerMapOutputData) {
        // On success, change centering of the map
        HomeSearchState homeSearchState = homeSearchViewModel.getState();
        homeSearchState.setStartPosition(centerMapOutputData.getGeoPosition());
        this.homeSearchViewModel.setState(homeSearchState);
        this.homeSearchViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(homeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // TODO
    }
}
