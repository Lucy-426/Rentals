package use_case.listing;

public class ListingInputData {

    private final String id;

    private String username = "";

    public ListingInputData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
