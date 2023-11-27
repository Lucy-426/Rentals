package entity;

import data_access.PropertyDataAccessObject;

public class Recommendations {

    String city;

    PropertyDataAccessObject properties;
    public Recommendations(String city){
        this.city = city;

    }
    // if filters use listings from filter
    // if no filter pull from cities


}
