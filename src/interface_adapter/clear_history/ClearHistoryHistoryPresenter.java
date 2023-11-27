package interface_adapter.clear_history;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.clear_history.ClearHistoryOutputBoundary;
import use_case.clear_history.ClearHistoryOutputData;

public class ClearHistoryHistoryPresenter implements ClearHistoryOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ClearHistoryViewModel clearHistoryViewModel;

    public ClearHistoryHistoryPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, ClearHistoryViewModel clearHistoryViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.clearHistoryViewModel = clearHistoryViewModel;
    }

    @Override
    public void prepareView(ClearHistoryOutputData response) {
        ClearHistoryState state = new ClearHistoryState();
        state.setClearMessage(ClearHistoryOutputData.message);
        clearHistoryViewModel.setState(state);
        clearHistoryViewModel.firePropertyChanged();
        searchViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
