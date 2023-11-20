package interface_adapter.listing_results;

import entity.Place;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class ListingResultsViewModel extends ViewModel {
    public final String TITLE_LABEL = "RESULTS";
    public final String BACK_BUTTON_LABEL = "Back";
    private ListingResultsState state = new ListingResultsState();
    private String city;
    private String filter;
    private ArrayList<String> placesName;


    public ListingResultsViewModel() {
        super("listing");
    }

    public ListingResultsState getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }

    public ArrayList<String> getPlacesName() {
        return placesName;
    }

    public void setState(ListingResultsState state) {
        this.state = state;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public void setPlacesName(ArrayList<String> placesName) {
        this.placesName = placesName;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
