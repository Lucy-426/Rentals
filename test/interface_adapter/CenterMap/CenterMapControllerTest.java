package interface_adapter.CenterMap;

import data_access.MapDataAccessObject;
import data_access.PropertyDataAccessObject;
import entity.PropertyFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import org.junit.jupiter.api.Test;
import use_case.CenterMap.CenterMapInputBoundary;
import use_case.CenterMap.CenterMapInteractor;
import use_case.CenterMap.CenterMapOutputBoundary;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CenterMapControllerTest {

    @Test
    void execute() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        HomeSearchViewModel homeSearchViewModel = new HomeSearchViewModel();
        MapDataAccessObject mapDataAccessObject = new MapDataAccessObject();

        CenterMapOutputBoundary centerMapOutputBoundary = new CenterMapPresenter(homeSearchViewModel, viewManagerModel);
        CenterMapInputBoundary centerMapInteractor = new CenterMapInteractor(mapDataAccessObject, centerMapOutputBoundary);

        CenterMapController centerMapController = new CenterMapController(centerMapInteractor);
        centerMapController.execute("1 Austin Terrace, Toronto, ON M5R 1X8");
    }
}