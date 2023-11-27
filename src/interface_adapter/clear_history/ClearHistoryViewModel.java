package interface_adapter.clear_history;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ClearHistoryViewModel extends ViewModel {

    private ClearHistoryState state = new ClearHistoryState();

    public ClearHistoryViewModel() {
        super("clear");
    }

    public void setState(ClearHistoryState state) {
        this.state = state;
    }

    public ClearHistoryState getState() {
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
