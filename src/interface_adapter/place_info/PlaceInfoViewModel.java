package interface_adapter.place_info;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlaceInfoViewModel extends ViewModel {

    public final String BACK_BUTTON_LABEL = "Back";
    private PlaceInfoState state = new PlaceInfoState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public PlaceInfoViewModel() {
        super("place_info");
    }

    public PlaceInfoState getState() {
        return state;
    }

    public void setState(PlaceInfoState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener Listener) {
        support.addPropertyChangeListener(Listener);
    }
}
