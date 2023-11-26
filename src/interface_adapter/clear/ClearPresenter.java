package interface_adapter.clear;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.clear_history.ClearOutputBoundary;
import use_case.clear_history.ClearOutputData;

public class ClearPresenter implements ClearOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ClearViewModel clearViewModel;

    public ClearPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, ClearViewModel clearViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.clearViewModel = clearViewModel;
    }

    @Override
    public void prepareView(ClearOutputData response) {
        ClearState state =new ClearState();
        state.setClearMessage(ClearOutputData.message);
        clearViewModel.setState(state);
        clearViewModel.firePropertyChanged();
        searchViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
