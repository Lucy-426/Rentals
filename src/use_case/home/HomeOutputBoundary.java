package use_case.home;

public interface HomeOutputBoundary {
    void prepareSuccessView(HomeOutputData homeOutputData);

    void displaySignupView();

    void displayLoginView();

    void displayProfile();

    void logOut();

    //TODO: add prepareFailView
}
