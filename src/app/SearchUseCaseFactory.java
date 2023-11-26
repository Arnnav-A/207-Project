package app;

import entity.CommonListingFactory;
import entity.ListingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.clear.ClearController;
import interface_adapter.clear.ClearPresenter;
import interface_adapter.clear.ClearViewModel;
import interface_adapter.get_history.GetHistoryController;
import interface_adapter.get_history.GetHistoryPresenter;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.save_history.SaveController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.clear_history.ClearDataAccessInterface;
import use_case.clear_history.ClearInputBoundary;
import use_case.clear_history.ClearInteractor;
import use_case.clear_history.ClearOutputBoundary;
import use_case.get_history.GetHistoryDataAccessInterface;
import use_case.get_history.GetHistoryInputBoundary;
import use_case.get_history.GetHistoryInteractor;
import use_case.get_history.GetHistoryOutputBoundary;
import use_case.save_history.SaveDataAccessInterface;
import use_case.save_history.SaveInputBoundary;
import use_case.save_history.SaveInteractor;
import use_case.search.SearchDataAccessInterface;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInteractor;
import use_case.search.SearchOutputBoundary;
import view.SearchView;

public class SearchUseCaseFactory {

    private SearchUseCaseFactory() {}

    public static SearchView create(
            ViewManagerModel viewManagerModel,
            SearchViewModel searchViewModel,
            ClearViewModel clearViewModel,
            ListingResultsViewModel listingResultsViewModel,
            SearchDataAccessInterface searchDataAccessObject,
            SaveDataAccessInterface saveDataAccessInterface,
            GetHistoryDataAccessInterface getHistoryDataAccessObject,
            ClearDataAccessInterface clearDataAccessObject) {

            SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, listingResultsViewModel, searchDataAccessObject);
            SaveController saveController = createSaveUseCase(saveDataAccessInterface);
            GetHistoryController getHistoryController = createGetHistoryUseCase(viewManagerModel, searchViewModel, getHistoryDataAccessObject);
            ClearController clearController = createClearUseCase(viewManagerModel, searchViewModel, clearViewModel, clearDataAccessObject);
            return new SearchView(searchViewModel, searchController, saveController, getHistoryController, clearController, clearViewModel);
        }

    private static SearchController createSearchUseCase(ViewManagerModel viewManagerModel,
                                                        SearchViewModel searchViewModel,
                                                        ListingResultsViewModel listingResultsViewModel,
                                                        SearchDataAccessInterface searchDataAccessObject) {

        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, listingResultsViewModel, viewManagerModel);

        ListingFactory listingFactory = new CommonListingFactory();

        SearchInputBoundary searchInteractor = new SearchInteractor(
                searchDataAccessObject, searchOutputBoundary, listingFactory);

        return new SearchController(searchInteractor);
    }

    private static SaveController createSaveUseCase(SaveDataAccessInterface saveDataAccessInterface) {

        SaveInputBoundary saveInteractor = new SaveInteractor(saveDataAccessInterface);

        return new SaveController(saveInteractor);
    }

    private static GetHistoryController createGetHistoryUseCase(ViewManagerModel viewManagerModel,
                                                                SearchViewModel searchViewModel,
                                                                GetHistoryDataAccessInterface getHistoryDataAccessObject) {

        GetHistoryOutputBoundary getHistoryOutputBoundary = new GetHistoryPresenter(searchViewModel, viewManagerModel);

        GetHistoryInputBoundary getHistoryInteractor = new GetHistoryInteractor(getHistoryDataAccessObject, getHistoryOutputBoundary);

        return new GetHistoryController(getHistoryInteractor);
    }

    private static ClearController createClearUseCase(ViewManagerModel viewManagerModel,
                                                      SearchViewModel searchViewModel,
                                                      ClearViewModel clearViewModel,
                                                      ClearDataAccessInterface clearDataAccessObject) {

        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(searchViewModel, viewManagerModel, clearViewModel);

        ClearInputBoundary clearInteractor = new ClearInteractor(clearDataAccessObject, clearOutputBoundary);

        return new ClearController(clearInteractor);
    }

}
