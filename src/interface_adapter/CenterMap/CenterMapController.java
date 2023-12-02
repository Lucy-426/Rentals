package interface_adapter.CenterMap;

import use_case.CenterMap.CenterMapInputBoundary;
import use_case.CenterMap.CenterMapInputData;

public class CenterMapController {
    final CenterMapInputBoundary centerMapUseCaseInteractor;

    public CenterMapController(CenterMapInputBoundary centerMapUseCaseInteractor) {
        this.centerMapUseCaseInteractor = centerMapUseCaseInteractor;
    }

    public void execute(String address) {
        CenterMapInputData centerMapInputData = new CenterMapInputData(address);

        centerMapUseCaseInteractor.execute(centerMapInputData);
    }
}
