package interface_adapter.get_saved_places;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetSavedViewModel extends ViewModel {

    private GetSavedState state = new GetSavedState();

    public GetSavedViewModel() {
        super("get saved places");
    }

    public void setState(GetSavedState state) {
        this.state = state;
    }

    public GetSavedState getState() {
        return this.state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
