package interface_adapter.listing_results;

import java.util.ArrayList;

public class ListingResultsState {

    private ArrayList<String> placesNames;
    public String city;
    public String filter;
    private String placeName;

    public ListingResultsState(ListingResultsState copy) {
        placesNames = copy.placesNames;
        city = copy.city;
        filter = copy.filter;
    }

    public ListingResultsState() {}

    public ArrayList<String> getPlacesNames() {
        return placesNames;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlacesNames(ArrayList<String> placesNames) {
        this.placesNames = placesNames;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
}
