package interface_adapter.listing_results;

import entity.Listing;

import java.util.ArrayList;

public class ListingResultsState {
    private ArrayList<String> places_names;
    public String city;
    public String filter;
    private String place_name;

    public ListingResultsState(ListingResultsState copy) {
        places_names = copy.places_names;
        city = copy.city;
        filter = copy.filter;
    }

    public ListingResultsState() {}

    public ArrayList<String> getPlacesNames() {
        return places_names;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }

    public String getPlaceName() {
        return place_name;
    }

    public void setPlacesNames(ArrayList<String> places_names) {
        this.places_names = places_names;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setPlaceName(String place_name) {
        this.place_name = place_name;
    }
}
