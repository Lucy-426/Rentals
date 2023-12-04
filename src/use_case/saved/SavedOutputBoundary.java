package use_case.saved;

public interface SavedOutputBoundary {
    void prepareSuccessView(SavedOutputData savedOutputData);

    void prepareFailView(String error);

    void displayHome();

    void logOut();
}
