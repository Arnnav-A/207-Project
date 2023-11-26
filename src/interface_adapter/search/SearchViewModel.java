package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {

    public final String TITLE_LABEL = "PlaceFinder";
    public final String CITY_NAME_LABEL = "City";
    public final String FILTER_LABEL = "Filter";
    public static final String SEARCH_BUTTON_LABEL = "Search";
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
