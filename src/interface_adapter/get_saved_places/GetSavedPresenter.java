package interface_adapter.get_saved_places;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchViewModel;
import use_case.get_saved_places.GetSavedOutputBoundary;
import use_case.get_saved_places.GetSavedOutputData;

public class GetSavedPresenter implements GetSavedOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final GetSavedViewModel getSavedViewModel;
    private ViewManagerModel viewManagerModel;

    public GetSavedPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel, GetSavedViewModel getSavedViewModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
        this.getSavedViewModel = getSavedViewModel;
    }

    @Override
    public void prepareView(GetSavedOutputData response) {
        GetSavedState state = new GetSavedState();
        state.setPlaces(response.getPlaces());
        getSavedViewModel.setState(state);
        searchViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
