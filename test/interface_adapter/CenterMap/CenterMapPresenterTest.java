package interface_adapter.CenterMap;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.junit.jupiter.api.Test;
import use_case.CenterMap.CenterMapOutputData;

import static org.junit.jupiter.api.Assertions.*;

class CenterMapPresenterTest {

    @Test
    void prepareSuccessView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        CenterMapPresenter centerMapPresenter = new CenterMapPresenter(homeSearchViewModel, viewManagerModel);

        CenterMapOutputData centerMapOutputData = new CenterMapOutputData(
                                                  new GeoPosition(43.6780371, -79.4094439),
                                     false);

        centerMapPresenter.prepareSuccessView(centerMapOutputData);
    }
}