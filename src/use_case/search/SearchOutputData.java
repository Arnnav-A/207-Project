package use_case.search;

import entity.Listing;

import java.util.ArrayList;

public class SearchOutputData {

    private final ArrayList<String> places_names;
    private final String city;
    private final String filter;
    private boolean useCaseFailed;

    public SearchOutputData(ArrayList<String> places_names, String city, String filter, boolean useCaseFailed) {
        this.places_names = places_names;
        this.city = city;
        this.filter = filter;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getPlacesNames() {
        return places_names;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }
}
