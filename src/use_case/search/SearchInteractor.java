package use_case.search;

import entity.Listing;
import entity.ListingFactory;
import entity.Place;

import java.util.ArrayList;

public class SearchInteractor implements SearchInputBoundary {

    final SearchDataAccessInterface searchDataAccessObject;
    final SearchOutputBoundary searchPresenter;
    final ListingFactory listingFactory;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessObject,
                            SearchOutputBoundary searchPresenter,
                            ListingFactory listingFactory) {
        this.searchDataAccessObject = searchDataAccessObject;
        this.searchPresenter = searchPresenter;
        this.listingFactory = listingFactory;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        if (!searchDataAccessObject.isValidFilter(searchInputData.getFilter())) {
            searchPresenter.prepareFailView("Filter not found.");
        } else if (!searchDataAccessObject.isValidCity(searchInputData.getCity())) {
            searchPresenter.prepareFailView("City not found.");
        } else {
            ArrayList<Place> points = searchDataAccessObject.getListing(searchInputData.getCity(),
                    searchInputData.getFilter());
            if (points.isEmpty()) {
                searchPresenter.prepareFailView("Places not found.");
            } else {
                Listing listing = listingFactory.create(points,
                        searchInputData.getCity(), searchInputData.getFilter());
                ArrayList<String> placesName = new ArrayList<>();
                for (Place place : listing.getPoints()) {
                    placesName.add(place.getName());
                }
                SearchOutputData searchOutputData = new SearchOutputData(placesName, listing.getCity(), listing.getFilter(), false);
                searchPresenter.prepareSuccessView(searchOutputData);
            }
        }
    }
}
