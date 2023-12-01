package use_case.saved;

public class SavedInteractor implements SavedInputBoundary {
    final SavedOutputBoundary savedPresenter;

    public SavedInteractor(SavedOutputBoundary savedOutputBoundary) {
        this.savedPresenter = savedOutputBoundary;
    }


    @Override
    public void execute(SavedInputData savedInputData) {


        SavedOutputData savedOutputData = new SavedOutputData("Apartment",60, 350, 8, 3, 2,
                300, true, false, "John Smith 4373294732");
        savedPresenter.prepareSuccessView(savedOutputData);

    }

    public void displayHome() { savedPresenter.displayHome(); }

    public void logOut() { savedPresenter.logOut(); }


}
