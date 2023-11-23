package interface_adapter.clear;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.clear_history.ClearOutputBoundary;
import use_case.clear_history.ClearOutputData;

public class ClearPresenter implements ClearOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClearPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareView(ClearOutputData response) {
        searchViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
        response.message();
    }
}
