package interface_adapter.get_history;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.get_history.GetHistoryOutputBoundary;
import use_case.get_history.GetHistoryOutputData;

public class GetHistoryPresenter implements GetHistoryOutputBoundary {
    private final SearchViewModel searchViewModel;
    private ViewManagerModel viewManagerModel;
    public GetHistoryPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareview(GetHistoryOutputData response) {
        searchViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
        response.message();
    }
}
