package use_case.saved;

public interface SavedInputBoundary {
    void execute(SavedInputData savedInputData);

    void displayHome();

    void logOut();

}
