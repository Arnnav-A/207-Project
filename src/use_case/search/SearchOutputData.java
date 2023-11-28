package use_case.search;

import java.util.ArrayList;

public class SearchOutputData {

    private final ArrayList<String> placesNames;
    private final String city;
    private final String filter;
    private boolean useCaseFailed;

    public SearchOutputData(ArrayList<String> placesNames, String city, String filter, boolean useCaseFailed) {
        this.placesNames = placesNames;
        this.city = city;
        this.filter = filter;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getPlacesNames() {
        return placesNames;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }
}
