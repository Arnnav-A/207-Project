package app;

import data_access.FilePlaceInfoDataAccessObject;
import data_access.FileSavePlacesDataAccessObject;
import data_access.FileSearchDataAccessObject;
import data_access.FileHistoryDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.clear_history.ClearHistoryViewModel;
import interface_adapter.get_history.GetHistoryViewModel;
import interface_adapter.get_saved_places.GetSavedViewModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.place_info.PlaceInfoViewModel;
import interface_adapter.search.SearchViewModel;

import view.GetFilterView;
import view.ListingView;
import view.PlaceInfoView;
import view.SearchView;
import view.ViewManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        JFrame application = new JFrame("Place Finder");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setMinimumSize(new Dimension(650,350));
        try {
            application.setIconImage(ImageIO.read(new File("src/assets/icon.jpeg")));
        } catch (IOException ignored) {}

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        SearchViewModel searchViewModel = new SearchViewModel();
        ClearHistoryViewModel clearHistoryViewModel = new ClearHistoryViewModel();
        GetHistoryViewModel getHistoryViewModel = new GetHistoryViewModel();
        ListingResultsViewModel listingResultsViewModel = new ListingResultsViewModel();
        PlaceInfoViewModel placeInfoViewModel = new PlaceInfoViewModel();
        GetFilterViewModel getFilterViewModel = new GetFilterViewModel();
        GetSavedViewModel getSavedViewModel = new GetSavedViewModel();

        FileSearchDataAccessObject searchDataAccessObject;
        searchDataAccessObject = new FileSearchDataAccessObject(new CommonPlaceFactory(), "src/data_access/filters.csv", "listingJSON.json");

        FilePlaceInfoDataAccessObject placeInfoDataAccessObject;
        placeInfoDataAccessObject = new FilePlaceInfoDataAccessObject("listingJSON.json", new CommonPlaceFactory());

        FileSavePlacesDataAccessObject savePlacesDataAccessObject;
        savePlacesDataAccessObject = new FileSavePlacesDataAccessObject("listingJSON.json", "savedCSV.csv", new CommonPlaceFactory());

        FileHistoryDataAccessObject historyDataAccessObject;
        historyDataAccessObject = new FileHistoryDataAccessObject("history.csv");

        SearchView searchView = SearchUseCaseFactory.create(viewManagerModel, searchViewModel, getFilterViewModel, clearHistoryViewModel, getHistoryViewModel, listingResultsViewModel, searchDataAccessObject, historyDataAccessObject, historyDataAccessObject, historyDataAccessObject, getSavedViewModel, savePlacesDataAccessObject);
        views.add(searchView, searchView.viewName);

        ListingView listingView = ListingUseCaseFactory.create(listingResultsViewModel, viewManagerModel, placeInfoDataAccessObject, placeInfoViewModel);
        views.add(listingView, listingView.viewName);

        PlaceInfoView placeInfoView = PlaceInfoUseCaseFactory.create(placeInfoViewModel, viewManagerModel, savePlacesDataAccessObject);
        views.add(placeInfoView, placeInfoView.viewname);

        GetFilterView getFilterView = new GetFilterView(getFilterViewModel, searchViewModel, viewManagerModel);
        views.add(getFilterView, getFilterView.viewName);


        viewManagerModel.setActiveView(searchView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setSize(300, 210);
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
