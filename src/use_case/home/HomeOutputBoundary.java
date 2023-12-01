package use_case.home;

import interface_adapter.saved.SavedState;

public interface HomeOutputBoundary {
    void prepareSuccessView(HomeOutputData homeOutputData);

    void displaySignupView();

    void displayLoginView();

    void displayProfile(SavedState savedState);

    void logOut();

    //TODO: add prepareFailView
}
