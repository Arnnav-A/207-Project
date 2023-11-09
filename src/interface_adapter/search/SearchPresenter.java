package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.listing_results.ListingResultsState;
import interface_adapter.listing_results.ListingResultsViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ListingResultsViewModel listingResultsViewModel;
    private ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel searchViewModel,
                           ListingResultsViewModel listingResultsViewModel,
                           ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.listingResultsViewModel = listingResultsViewModel;
        this.viewManagerModel = viewManagerModel;
    }



    @Override
    public void prepareSuccessView(SearchOutputData response) {
        ListingResultsState listingResultsState = this.listingResultsViewModel.getState();
        listingResultsState.setListing(response.getListing());
        listingResultsState.setCity(response.getCity());
        listingResultsState.setFilter(response.getFilter());
        this.listingResultsViewModel.setState(listingResultsState);
        this.listingResultsViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(listingResultsViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }


    @Override
    public void prepareFailView(String error) {
        SearchState searchState = searchViewModel.getState();
        searchState.setError(error);
        searchViewModel.firePropertyChanged();
    }
}
