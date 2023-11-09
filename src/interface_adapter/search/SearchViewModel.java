package interface_adapter.search;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchViewModel extends ViewModel {
    public static final String SEARCH_BUTTON_LABEL = "Search";
    public final String TITLE_LABEL = "Search View";
    public final String CITY_NAME_LABEL = "Choose city name";
    public final String FILTER_LABEL = "Choose filter";
    private SearchState state = new SearchState();
    public SearchViewModel() {
        super("search");
    }
    public void setState(SearchState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener Listener) {
        support.addPropertyChangeListener(Listener);
    }

    public SearchState getState() {
        return state;
    }
}
