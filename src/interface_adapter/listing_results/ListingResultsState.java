package interface_adapter.listing_results;

import entity.Listing;

import java.util.ArrayList;

public class ListingResultsState {
    private ArrayList<String> placesName;
    public String city;
    public String filter;

    public ListingResultsState(ListingResultsState copy) {
        placesName = copy.placesName;
        city = copy.city;
        filter = copy.filter;
    }

    public ListingResultsState() {}

    public ArrayList<String> getPlacesName() {
        return placesName;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }

    public void setPlacesName(ArrayList<String> placesName) {
        this.placesName = placesName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
