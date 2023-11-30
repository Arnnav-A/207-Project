package app;

import entity.CommonListingFactory;
import entity.ListingFactory;
import interface_adapter.ViewManagerModel;

import interface_adapter.getFilter.GetFilterController;
import interface_adapter.getFilter.GetFilterPresenter;
import interface_adapter.getFilter.GetFilterViewModel;
import interface_adapter.clear_history.ClearHistoryController;
import interface_adapter.clear_history.ClearHistoryHistoryPresenter;
import interface_adapter.clear_history.ClearHistoryViewModel;
import interface_adapter.get_history.GetHistoryController;
import interface_adapter.get_history.GetHistoryPresenter;
import interface_adapter.get_history.GetHistoryViewModel;
import interface_adapter.get_saved_places.GetSavedController;
import interface_adapter.get_saved_places.GetSavedPresenter;
import interface_adapter.get_saved_places.GetSavedViewModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.save_history.SaveController;
import interface_adapter.search.SearchController;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import use_case.getFilter.GetFilterInteractor;
import use_case.getFilter.GetFilterOutputBoundary;
import use_case.clear_history.ClearHistoryDataAccessInterface;
import use_case.clear_history.ClearHistoryInputBoundary;
import use_case.clear_history.ClearHistoryHistoryInteractor;
import use_case.clear_history.ClearHistoryOutputBoundary;
import use_case.get_history.GetHistoryDataAccessInterface;
import use_case.get_history.GetHistoryInputBoundary;
import use_case.get_history.GetHistoryInteractor;
import use_case.get_history.GetHistoryOutputBoundary;
import use_case.get_saved_places.GetSavedDataAccessInterface;
import use_case.get_saved_places.GetSavedInputBoundary;
import use_case.get_saved_places.GetSavedInteractor;
import use_case.get_saved_places.GetSavedOutputBoundary;
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
            GetFilterViewModel getFilterViewModel,
            ClearHistoryViewModel clearHistoryViewModel,
            GetHistoryViewModel getHistoryViewModel,
            ListingResultsViewModel listingResultsViewModel,
            SearchDataAccessInterface searchDataAccessObject,
            SaveDataAccessInterface saveDataAccessInterface,
            GetHistoryDataAccessInterface getHistoryDataAccessObject,
            ClearHistoryDataAccessInterface clearDataAccessObject,
            GetSavedViewModel getSavedViewModel,
            GetSavedDataAccessInterface getSavedDataAccessObject) {

        SearchController searchController = createSearchUseCase(viewManagerModel, searchViewModel, listingResultsViewModel, searchDataAccessObject);
        GetFilterController getFilterController = createGetFilterController(viewManagerModel, getFilterViewModel, searchDataAccessObject);
        SaveController saveController = createSaveUseCase(saveDataAccessInterface);
        GetHistoryController getHistoryController = createGetHistoryUseCase(viewManagerModel, searchViewModel, getHistoryViewModel, getHistoryDataAccessObject);
        ClearHistoryController clearHistoryController = createClearUseCase(viewManagerModel, searchViewModel, clearHistoryViewModel, clearDataAccessObject);
        GetSavedController getSavedController = createGetSavedUseCase(viewManagerModel, searchViewModel, getSavedViewModel, getSavedDataAccessObject);
        return new SearchView(searchViewModel, searchController, getFilterController, saveController, getHistoryController, clearHistoryController, clearHistoryViewModel, getHistoryViewModel, getSavedController, getSavedViewModel);
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

    private static GetFilterController createGetFilterController(ViewManagerModel viewManagerModel,
                                                                 GetFilterViewModel getFilterViewModel,
                                                                 SearchDataAccessInterface searchDataAccessObject) {
        GetFilterOutputBoundary getFilterOutputBoundary = new GetFilterPresenter(getFilterViewModel, viewManagerModel);
        GetFilterInteractor getFilterUseCaseInteractor = new GetFilterInteractor(searchDataAccessObject, getFilterOutputBoundary);
        return new GetFilterController(getFilterUseCaseInteractor);
    }

    private static SaveController createSaveUseCase(SaveDataAccessInterface saveDataAccessInterface) {

        SaveInputBoundary saveInteractor = new SaveInteractor(saveDataAccessInterface);

        return new SaveController(saveInteractor);
    }

    private static GetHistoryController createGetHistoryUseCase(ViewManagerModel viewManagerModel,
                                                                SearchViewModel searchViewModel,
                                                                GetHistoryViewModel getHistoryViewModel,
                                                                GetHistoryDataAccessInterface getHistoryDataAccessObject) {

        GetHistoryOutputBoundary getHistoryOutputBoundary = new GetHistoryPresenter(searchViewModel, viewManagerModel, getHistoryViewModel);

        GetHistoryInputBoundary getHistoryInteractor = new GetHistoryInteractor(getHistoryDataAccessObject, getHistoryOutputBoundary);

        return new GetHistoryController(getHistoryInteractor);
    }

    private static ClearHistoryController createClearUseCase(ViewManagerModel viewManagerModel,
                                                             SearchViewModel searchViewModel,
                                                             ClearHistoryViewModel clearHistoryViewModel,
                                                             ClearHistoryDataAccessInterface clearDataAccessObject) {

        ClearHistoryOutputBoundary clearHistoryOutputBoundary = new ClearHistoryHistoryPresenter(searchViewModel, viewManagerModel, clearHistoryViewModel);

        ClearHistoryInputBoundary clearInteractor = new ClearHistoryHistoryInteractor(clearDataAccessObject, clearHistoryOutputBoundary);

        return new ClearHistoryController(clearInteractor);
    }

    private static GetSavedController createGetSavedUseCase(ViewManagerModel viewManagerModel, SearchViewModel searchViewModel, GetSavedViewModel getSavedViewModel, GetSavedDataAccessInterface getSavedDataAccessObject) {
        GetSavedOutputBoundary getSavedOutputBoundary = new GetSavedPresenter(searchViewModel, viewManagerModel, getSavedViewModel);

        GetSavedInputBoundary getSavedInteractor = new GetSavedInteractor(getSavedDataAccessObject, getSavedOutputBoundary);

        return  new GetSavedController(getSavedInteractor);
    }
}
