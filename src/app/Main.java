package app;

import data_access.FileSearchDataAccessObject;
import entity.*;
import interface_adapter.search.SearchController;
import use_case.search.SearchDataAccessInterface;
import use_case.search.SearchInputBoundary;

import use_case.search.SearchInteractor;

public class Main {

    public static void main(String[] args) {
        ListingFactory listingFactory = new CommonListingFactory();
        CommonPlaceFactory placeFactory = new CommonPlaceFactory();
        SearchDataAccessInterface fileSearchDataAccessObject = new FileSearchDataAccessObject(placeFactory, "src/data_access/filters.csv", "listingJSON.json");
        SearchInputBoundary searchInteractor = new SearchInteractor(fileSearchDataAccessObject, listingFactory);
        SearchController searchController = new SearchController(searchInteractor);
        searchController.execute("Toronto", "restaurant");
    }

}
