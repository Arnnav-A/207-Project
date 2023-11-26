package app;

import data_access.FilePlaceInfoDataAccessObject;
import data_access.FileSearchDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.place_info.PlaceInfoViewModel;
import interface_adapter.search.SearchViewModel;

import view.ListingView;
import view.PlaceInfoView;
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
        PlaceInfoViewModel placeInfoViewModel = new PlaceInfoViewModel();

        FileSearchDataAccessObject searchDataAccessObject;
        searchDataAccessObject = new FileSearchDataAccessObject(new CommonPlaceFactory(), "src/data_access/filters.csv", "listingJSON.json");

        FilePlaceInfoDataAccessObject placeInfoDataAccessObject;
        placeInfoDataAccessObject = new FilePlaceInfoDataAccessObject("listingJSON.json", new CommonPlaceFactory());

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, listingResultsViewModel, searchDataAccessObject);
        views.add(searchView, searchView.viewName);

        ListingView listingView = ListingUseCaseFactory.create(listingResultsViewModel, viewManagerModel, placeInfoDataAccessObject, placeInfoViewModel);
        views.add(listingView, listingView.viewName);

        PlaceInfoView placeInfoView = new PlaceInfoView(placeInfoViewModel, viewManagerModel);
        views.add(placeInfoView, placeInfoView.viewname);

        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
