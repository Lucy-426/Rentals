package use_case.home;

import java.util.HashMap;

public class HomeOutputData {
    private final HashMap<String, String> displayedProperties;

    public HomeOutputData(HashMap<String, String> displayedProperties) {
        this.displayedProperties = displayedProperties;
    }

    public HashMap<String, String> getDisplayedProperties() {
        return displayedProperties;
    }
}
