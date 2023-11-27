package use_case.home;

public interface HomeOutputBoundary {
    void prepareSuccessView(HomeOutputData homeOutputData);

    void prepareListingView(HomeOutputData homeOutputData);

}
