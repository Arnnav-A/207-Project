package app;

import entity.CommonListingFactory;
import entity.ListingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.getFilter.GetFilterController;
import interface_adapter.getFilter.GetFilterPresenter;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.getFilter.GetFilterDataAccessInterface;
import use_case.getFilter.GetFilterInteractor;
import use_case.getFilter.GetFilterOutputBpundary;
import use_case.search.SearchDataAccessInterface;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import view.SearchView;

import javax.swing.*;
import java.io.IOException;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            GetFilterViewModel getFilterViewModel,
            ListingResultsViewModel listingResultsViewModel,
            SearchDataAccessInterface searchDataAccessObject) {

        try {
            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, listingResultsViewModel, searchDataAccessObject);
            GetFilterController getFilterController = createGetFilterController(viewManagerModel, getFilterViewModel,searchDataAccessObject);
            return new SearchView(searchViewModel, searchController, getFilterController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel,
                                                        SearchViewModel searchViewModel,
                                                        ListingResultsViewModel listingResultsViewModel,
                                                        SearchDataAccessInterface searchDataAccessObject) throws IOException {

        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, listingResultsViewModel, viewManagerModel);

        ListingFactory listingFactory = new CommonListingFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                searchDataAccessObject, searchOutputBoundary, listingFactory);

        return new SearchController(searchInteractor);
    }

    private static GetFilterController createGetFilterController(ViewManagerModel viewManagerModel,
                                                                 GetFilterViewModel getFilterViewModel,
                                                                 SearchDataAccessInterface searchDataAccessObject) {
        GetFilterOutputBpundary getFilterOutputBpundary = new GetFilterPresenter(getFilterViewModel, viewManagerModel);
        GetFilterInteractor getFilterUseCaseInteractor = new GetFilterInteractor(searchDataAccessObject, getFilterOutputBpundary);
        return new GetFilterController(getFilterUseCaseInteractor);
    }
}
