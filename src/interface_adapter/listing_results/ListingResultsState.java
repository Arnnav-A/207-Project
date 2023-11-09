package interface_adapter.listing_results;

import entity.Listing;

public class ListingResultsState {
    private Listing listing;

    public ListingResultsState(ListingResultsState copy) {listing = copy.listing;}

    public ListingResultsState() {}

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }
}
