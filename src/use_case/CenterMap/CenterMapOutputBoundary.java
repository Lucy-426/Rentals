package use_case.CenterMap;

public interface CenterMapOutputBoundary {
    void prepareSuccessView(CenterMapOutputData centerMapOutputData);
    void prepareFailView(String error);
}
