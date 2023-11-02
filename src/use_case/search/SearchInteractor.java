package use_case.search;

import entity.Listing;
import entity.ListingFactory;
import entity.Place;

import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary {
    final SearchDataAccessInterface searchDataAccessObject;
    // final SearchOutputBoundary searchPresenter;
    final ListingFactory listingFactory;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessObject,
                            // SearchOutputBoundary searchPresenter,
                            ListingFactory listingFactory) {
        this.searchDataAccessObject = searchDataAccessObject;
        // this.searchPresenter = searchPresenter;
        this.listingFactory = listingFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        if (!searchDataAccessObject.isValidFilter(searchInputData.getFilter())) {
            // searchPresenter.prepareFailView("Filter not found.");
            System.out.println("Filter not found."); // for the sake of testing
        } else {
            ArrayList<Place> points = searchDataAccessObject.getListing(searchInputData.getCity(),
                    searchInputData.getFilter());
            if (points.isEmpty()) {
                // searchPresenter.prepareFailView("City or places not found.");
                System.out.println("City or places not found."); // for the sake of testing
            } else {
                Listing listing = listingFactory.create(points,
                        searchInputData.getCity(), searchInputData.getFilter());
                SearchOutputData searchOutputData = new SearchOutputData(listing, false);
                // searchPresenter.prepareSuccessView(searchOutputData);
                for (Place place : listing.getPoints()) {
                    System.out.println(place.getName() + " - " + place.getAddress()); // for the sake of testing
                }
            }
        }
    }
}
