package app;

import data_access.FileSearchDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.applyFilter.ApplyFilterController;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.search.SearchViewModel;

import view.GetFilterView;
import view.ListingView;
import view.SearchView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame application = new JFrame("Place Finder");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchViewModel searchViewModel = new SearchViewModel();
        ListingResultsViewModel listingResultsViewModel = new ListingResultsViewModel();
        GetFilterViewModel getFilterViewModel = new GetFilterViewModel();

        FileSearchDataAccessObject searchDataAccessObject;
        searchDataAccessObject = new FileSearchDataAccessObject(new CommonPlaceFactory(), "src/data_access/filters.csv", "listingJSON.json");

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, getFilterViewModel,listingResultsViewModel, searchDataAccessObject);
        views.add(searchView, searchView.viewName);

        ListingView listingView = new ListingView(listingResultsViewModel);
        views.add(listingView, listingView.viewName);

        GetFilterView getFilterView = new GetFilterView(getFilterViewModel, searchViewModel, viewManagerModel);
        views.add(getFilterView, getFilterView.viewName);

        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(300, 210);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }

    //ListingFactory listingFactory = new CommonListingFactory();
    //CommonPlaceFactory placeFactory = new CommonPlaceFactory();
    //SearchDataAccessInterface fileSearchDataAccessObject = new FileSearchDataAccessObject(placeFactory, "src/data_access/filters.csv", "listingJSON.json");
    //SearchInputBoundary searchInteractor = new SearchInteractor(fileSearchDataAccessObject, listingFactory);
    //SearchController searchController = new SearchController(searchInteractor);
        //searchController.execute("Toronto", "winery"); //change the city name and the filter to test it!
}
