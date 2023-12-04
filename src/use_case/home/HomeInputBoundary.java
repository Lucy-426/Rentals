package use_case.home;

import interface_adapter.saved.SavedState;

public interface HomeInputBoundary {
    void execute(HomeInputData homeInputData);

    void displaySignupView();

    void displayLoginView();

    void displayProfile(SavedState savedState);

    void logOut();
}
