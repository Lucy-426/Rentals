package interface_adapter.saved;

import use_case.listing.ListingInputBoundary;
import use_case.listing.ListingInputData;
import use_case.saved.SavedInputBoundary;
import use_case.saved.SavedInputData;

public class SavedController {
    final SavedInputBoundary savedInteractor;

    public SavedController(SavedInputBoundary savedInteractor) {
        this.savedInteractor = savedInteractor;
    }

    public void execute(String rentalType, int daysListedAgo, float price, int walkScore, int numBed, int numBath,
                        int squareFeet, boolean furnished, boolean parking, String contact){
        SavedInputData savedInputData = new SavedInputData(rentalType, daysListedAgo, price, walkScore, numBed, numBath,
                squareFeet, furnished, parking, contact);

        savedInteractor.execute(savedInputData);
    }

    public void displayHome() { savedInteractor.displayHome(); }

    public void logOut() { savedInteractor.logOut(); }
}
