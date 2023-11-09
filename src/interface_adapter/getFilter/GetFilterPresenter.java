package interface_adapter.getFilter;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.getFilter.GetFilterOutputBpundary;

public class GetFilterPresenter implements GetFilterOutputBpundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetFilterPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareView(String info) {
        SearchState searchState = searchViewModel.getState();
        searchState.setNotice(info);
        searchViewModel.firePropertyChanged();
    }
}
