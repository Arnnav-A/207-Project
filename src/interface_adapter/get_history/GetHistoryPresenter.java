package interface_adapter.get_history;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.get_history.GetHistoryOutputBoundary;
import use_case.get_history.GetHistoryOutputData;

public class GetHistoryPresenter implements GetHistoryOutputBoundary {
    private final SearchViewModel searchViewModel;
    private final GetHistoryViewModel getHistoryViewModel;
    private ViewManagerModel viewManagerModel;
    public GetHistoryPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, GetHistoryViewModel getHistoryViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.getHistoryViewModel = getHistoryViewModel;
    }

    @Override
    public void prepareview(GetHistoryOutputData response) {
        GetHistoryState state = new GetHistoryState();
        state.setHistory(response.getUserHistory());
        getHistoryViewModel.setState(state);
        searchViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
