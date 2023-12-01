package use_case.home;

public interface HomeInputBoundary {
    void execute(HomeInputData homeInputData);

    void displaySignupView();

    void displayLoginView();

    void displayProfile();

    void logOut();
}
