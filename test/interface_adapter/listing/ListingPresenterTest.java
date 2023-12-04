package interface_adapter.listing;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.listing.ListingOutputData;

class ListingPresenterTest {

    @Test
    void prepareSuccessView() {
        ViewManagerModel viewManagerModel = new ViewManagerModel();

        ListingViewModel listingViewModel = new ListingViewModel();

        ListingPresenter listingPresenter = new ListingPresenter(viewManagerModel, listingViewModel);

        ListingOutputData listingOutputData = new ListingOutputData("testID", "New York", "123 Smith St",
                "3", "400", "2", "7", "No", "Rental", null);
        listingPresenter.prepareSuccessView(listingOutputData);
    }
}