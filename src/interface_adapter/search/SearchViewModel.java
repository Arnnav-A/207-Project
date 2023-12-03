package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public final String TITLE_LABEL = "PlaceFinder";
    public final String CITY_NAME_LABEL = "City";
    public final String FILTER_LABEL = "Filter";
    public final String SEARCH_BUTTON_LABEL = "Search";
    public final String GET_FILTER_LABEL = "Get Filter";
    public final String GET_HISTORY_BUTTON_LABEL = "Get history";
    public final String CLEAR_HISTORY_BUTTON_LABEL = "Clear history";
    public final String GET_SAVED_PLACES_BUTTON_LABEL = "Saved Places";
    private SearchState state = new SearchState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchViewModel() {
        super("search");
    }

    public void setState(SearchState state) {
        this.state = state;
    }

    public SearchState getState() { return state; }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener Listener) {
        support.addPropertyChangeListener(Listener);
    }
}
