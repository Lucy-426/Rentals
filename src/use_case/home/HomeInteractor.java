package use_case.home;

import entity.Property;
import entity.PropertyFactory;

public class HomeInteractor implements HomeInputBoundary {

    final HomeSearchDataAccessInterface homeDataAccessObject;

    final HomeOutputBoundary homePresenter;

    final PropertyFactory propertyFactory;

    public HomeInteractor(HomeSearchDataAccessInterface homesearchDataAccessInterface,
                          HomeOutputBoundary homeOutputBoundary, PropertyFactory propertyFactory) {
        this.homeDataAccessObject = homesearchDataAccessInterface;
        this.homePresenter = homeOutputBoundary;
        this.propertyFactory = propertyFactory;
    }
    @Override
    public void execute(HomeInputData homeInputData) {
        // TODO: change input/output data so it's not hard coded, and
        //  also change so it doesn't create a property but rather passes it to a filter

        Property property = propertyFactory.create(homeInputData.getId(), homeInputData.getCity(), homeInputData.getAddress(),
                homeInputData.getNumRooms(), homeInputData.getPriceRange(), homeInputData.getNumBaths(),
                homeInputData.getWalkScore(), homeInputData.getFurnished(), homeInputData.getListingType());
        homeDataAccessObject.save(property);

        System.out.println("id: " + homeInputData.getId());
        System.out.println("city: " + homeInputData.getCity());
        System.out.println("address: " + homeInputData.getAddress());
        System.out.println("rooms: " + homeInputData.getNumRooms());
        System.out.println("price range: " + homeInputData.getPriceRange());
        System.out.println("bathrooms: " + homeInputData.getNumBaths());
        System.out.println("walk score: " + homeInputData.getWalkScore());
        System.out.println("furnished/not furnished: " + homeInputData.getFurnished());
        System.out.println("listing type: " + homeInputData.getListingType());

        // TODO: filtering step goes here, output data should be the filtered properties
        HomeOutputData homeOutputData = new HomeOutputData("address", "2", "1000-1500", "1", "1-3", "furnished", "apartment");
        homePresenter.prepareSuccessView(homeOutputData);
    }
}
