package use_case.search;

import entity.Listing;

public class SearchOutputData {

    private final Listing listing;

    private final String city;

    private final String filter;

    private boolean useCaseFailed;

    public SearchOutputData(Listing listing, String city, String filter, boolean useCaseFailed) {
        this.listing = listing;
        this.city = city;
        this.filter = filter;
        this.useCaseFailed = useCaseFailed;
    }


    public Listing getListing() {
        return listing;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }
}
