package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class SearchController {

    final SearchInputBoundary searchUseCaseInteractor;

    public SearchController(SearchInputBoundary searchUseCaseInteractor) {
        this.searchUseCaseInteractor = searchUseCaseInteractor;
    }

    public void execute(String city, String filter) {
        SearchInputData searchInputData = new SearchInputData(city, filter);
        searchUseCaseInteractor.execute(searchInputData);
    }
}
