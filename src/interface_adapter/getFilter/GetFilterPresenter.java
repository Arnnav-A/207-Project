package interface_adapter.getFilter;

import interface_adapter.ViewManagerModel;
import use_case.getFilter.GetFilterOutputBoundary;
import use_case.getFilter.GetFilterOutputData;

public class GetFilterPresenter implements GetFilterOutputBoundary {

    private final GetFilterViewModel getFilterViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetFilterPresenter(GetFilterViewModel getFilterViewModel, ViewManagerModel viewManagerModel) {
        this.getFilterViewModel = getFilterViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareView(GetFilterOutputData filter) {
        GetFilterState getFilterState = getFilterViewModel.getState();
        getFilterState.setParentFilter(filter.getParentFilters());
        getFilterViewModel.setAllFilters(filter.getAllFilters());
        getFilterViewModel.setState(getFilterState);
        getFilterViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(getFilterViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
