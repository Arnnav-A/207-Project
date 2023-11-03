package use_case.search;

import entity.Listing;

public class SearchOutputData {

    private final Listing listing;

    private boolean useCaseFailed;

    public SearchOutputData(Listing listing, boolean useCaseFailed) {
        this.listing = listing;
        this.useCaseFailed = useCaseFailed;
    }

    public Listing getListing() {
        return listing;
    }
}
