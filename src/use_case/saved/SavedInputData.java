package use_case.saved;

public class SavedInputData {
    // should we just have a property id all pull all the data from that
//    private final String propertyId;
//
//    public ListingInputData(String propertyId) {
//        this.propertyId = propertyId;
//    }
    // or should we list all the data
    private final String id;

    public SavedInputData(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
