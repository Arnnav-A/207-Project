package interface_adapter.listing_results;

import entity.Listing;

public class ListingResultsState {
    private Listing listing;
    public String city;
    public String filter;

    public ListingResultsState(ListingResultsState copy) {listing = copy.listing;}

    public ListingResultsState() {}

    public Listing getListing() {
        return listing;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
