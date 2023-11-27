package interface_adapter.get_history;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetHistoryViewModel extends ViewModel {
    private GetHistoryState state = new GetHistoryState();

    public GetHistoryViewModel() {
        super("get history");
    }

    public void setState(GetHistoryState state) {
        this.state = state;
    }

    public GetHistoryState getState() {
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
