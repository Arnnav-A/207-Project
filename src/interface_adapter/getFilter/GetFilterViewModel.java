package interface_adapter.getFilter;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class GetFilterViewModel extends ViewModel {

    public final String APPLY_BUTTON_LABEL = "Apply this Filter";
    public final String CLOSE_BUTTON_LABEL = "Close this window";
    public final String TITLE_LABEL = "All Filters View";
    private GetFilterState state = new GetFilterState();
    private String[] parentFilter= {"Please select a Filter"};
    private String[] subFilters_1 = {"Please select a Filter"};
    private String[] subFilters_2 = {"Please select a Filter"};
    private String[] subFilters_3 = {"Please select a Filter"};

    private String selectedFilter;

    public GetFilterViewModel() {
        super("getFilter");
    }

    public GetFilterState getState() {
        return state;
    }

    public String[] getParentFilter() {
        return parentFilter;
    }

    public String getSelectedFilter() {
        return selectedFilter;
    }

    public String[] getSubFilters_1() {
        return subFilters_1;
    }

    public String[] getSubFilters_2() {
        return subFilters_2;
    }

    public String[] getSubFilters_3() {
        return subFilters_3;
    }

    public void setState(GetFilterState state) {
        this.state = state;
    }

    public void setParentFilter(String[] parentFilter) {
        this.parentFilter = parentFilter;
    }

    public void setSelectedFilter(String selectedFilter) {
        this.selectedFilter = selectedFilter;
    }

    public void setSubFilters_1(String[] subFilters) {
        this.subFilters_1 = subFilters;
    }

    public void setSubFilters_2(String[] subFilters) {
        this.subFilters_2 = subFilters;
    }

    public void setSubFilters_3(String[] subFilters) {
        this.subFilters_3 = subFilters;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state",null, this.state);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
