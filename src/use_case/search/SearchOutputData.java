package use_case.search;

import entity.Listing;

import java.util.ArrayList;

public class SearchOutputData {

    private final ArrayList<String> placesName;

    private final String city;

    private final String filter;

    private boolean useCaseFailed;

    public SearchOutputData(ArrayList<String> places_name, String city, String filter, boolean useCaseFailed) {
        this.placesName = places_name;
        this.city = city;
        this.filter = filter;
        this.useCaseFailed = useCaseFailed;
    }


    public ArrayList<String> getPlacesName() {
        return placesName;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }
}
