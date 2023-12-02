package use_case.home;

import entity.Property;
import entity.PropertyFactory;

import java.util.HashMap;

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

        Property property = propertyFactory.create(homeInputData.getId(), homeInputData.getCity(), homeInputData.getAddress(),
                homeInputData.getNumRooms(), homeInputData.getPriceRange(), homeInputData.getNumBaths(),
                homeInputData.getWalkScore(), homeInputData.getFurnished(), homeInputData.getListingType());
        homeDataAccessObject.save(property);

<<<<<<< HEAD
=======
        System.out.println("id: " + homeInputData.getId());
        System.out.println("city: " + homeInputData.getCity());
        System.out.println("address: " + homeInputData.getAddress());
        if (!(homeInputData.getAddress()==null) && homeInputData.getAddress().isEmpty()) {
            System.out.println("address: is an empty string");
        } else {
            System.out.println("address: " + homeInputData.getAddress());
        }
        System.out.println("rooms: " + homeInputData.getNumRooms());
        System.out.println("price range: " + homeInputData.getPriceRange());
        System.out.println("bathrooms: " + homeInputData.getNumBaths());
        System.out.println("walk score: " + homeInputData.getWalkScore());
        System.out.println("furnished/not furnished: " + homeInputData.getFurnished());
        System.out.println("listing type: " + homeInputData.getListingType());

>>>>>>> origin/develop
        homeDataAccessObject.filter();
        HashMap<String, String> displayedProperties = homeDataAccessObject.getFilteredProperties();
        HomeOutputData homeOutputData = new HomeOutputData(displayedProperties);
        homePresenter.prepareSuccessView(homeOutputData);
    }

}
