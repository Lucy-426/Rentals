package use_case.saved;

public interface SavedOutputBoundary {
    void prepareSuccessView(SavedOutputData savedOutputData);

    void prepareListingView(SavedOutputData savedOutputData);
}
