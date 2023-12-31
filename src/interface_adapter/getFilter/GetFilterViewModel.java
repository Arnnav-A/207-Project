package interface_adapter.getFilter;

import entity.CommonFilter;
import entity.Filter;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class GetFilterViewModel extends ViewModel {

    public final String APPLY_BUTTON_LABEL = "Apply Selected Filter";
    public final String RESET_BUTTON_LABEL = "Reset Selection";
    public final String TITLE_LABEL = "All Filters View";
    private GetFilterState state = new GetFilterState();
    private String[] parentFilter= {"Please select a Filter"};
    private String[] subFilters_1 = {"Please select a Filter"};
    private String[] subFilters_2 = {"Please select a Filter"};
    private String[] subFilters_3 = {"Please select a Filter"};
    private final String[] defaultSubFilter = {"Please select a Filter"};
    private String selectedSubFilter1 = "";
    private String selectedSubFilter2 = "";
    private String selectedSubFilter3 = "";
    private String selectedParentFilter = "";
    private Filter allFilters;

    public GetFilterViewModel() {
        super("getFilter");
    }

    public GetFilterState getState() {
        return state;
    }

    public void setState(GetFilterState state) {
        this.state = state;
    }

    public void setParentFilter() {
        this.parentFilter = state.getParentFilter();
        this.selectedSubFilter1 = "";
        this.selectedSubFilter2 = "";
        this.selectedSubFilter3 = "";
    }

    public void setSelectedParentFilter() {
        this.selectedParentFilter = state.getSelectedParentFilter();
    }

    public void setSelectedSubFilter1(String selectedSubFilter1) {
        this.selectedSubFilter1 = selectedSubFilter1;
    }

    public void setSelectedSubFilter2(String selectedSubFilter2) {
        this.selectedSubFilter2 = selectedSubFilter2;
    }

    public void setSelectedSubFilter3(String selectedSubFilter3) {
        this.selectedSubFilter3 = selectedSubFilter3;
    }

    public void setAllFilters(Filter filters) {
        this.allFilters = filters;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state",null, this.state);
    }


    public void parentFilterSelectionChanged() {
        String selectedParentFilter = this.selectedParentFilter;
        System.out.println("ViewModel has parentFilter chose: " + selectedParentFilter);
        ArrayList<String> sublist = allFilters.getSubFilter(selectedParentFilter).getSubFilterNames();
        sublist.add(0, "Please select a Filter");
        this.subFilters_1 = sublist.toArray(new String[0]);
        state.setSubFilter1(subFilters_1);
        state.setSubFilter2(defaultSubFilter);
        state.setSubFilter3(defaultSubFilter);
        state.setSelectedSubFilter1(null);
        state.setSelectedSubFilter2(null);
        state.setSelectedSubFilter3(null);
    }


    public void subFilter1SelectionChanged() {
        if (!selectedSubFilter1.equals("Please select a Filter")) {
            String selectedSubFilter1 = this.selectedSubFilter1;
            System.out.println("ViewModel has subFilter1 chose: " + selectedSubFilter1);
            ArrayList<String> sublist = allFilters.getSubFilter(selectedParentFilter).getSubFilter(selectedSubFilter1).getSubFilterNames();
            sublist.add(0, "Please select a Filter");
            this.subFilters_2 = sublist.toArray(new String[0]);
            state.setSubFilter2(subFilters_2);
            state.setSubFilter3(new String[]{"Please select a Filter"});
            state.setSelectedSubFilter2(null);
            state.setSelectedSubFilter3(null);
        } else {
            state.setSubFilter2(defaultSubFilter);
            state.setSubFilter3(defaultSubFilter);
            state.setSelectedSubFilter2(null);
            state.setSelectedSubFilter3(null);
        }
    }


    public void subFilter2SelectionChanged() {
        if (!selectedSubFilter2.equals("Please select a Filter")) {
            String selectedSubFilter2 = this.selectedSubFilter2;
            System.out.println("ViewModel has subFilter2 chose: " + selectedSubFilter2);
            ArrayList<String> sublist = allFilters.getSubFilter(selectedParentFilter).getSubFilter(this.selectedSubFilter1).getSubFilter(selectedSubFilter2).getSubFilterNames();
            sublist.add(0, "Please select a Filter");
            this.subFilters_3 = sublist.toArray(new String[0]);
            state.setSubFilter3(subFilters_3);
            state.setSelectedSubFilter3(null);
        } else {
            state.setSubFilter3(defaultSubFilter);
            state.setSelectedSubFilter3(null);
        }
    }


    public void subFilter3SelectionChanged() {
        if (!selectedSubFilter3.equals("Please select a Filter")) {
            String selectedSubFilter3 = this.selectedSubFilter3;
            System.out.println("ViewModel has subFilter3 chose: " + selectedSubFilter3);
        }
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void resetSelection() {
        this.parentFilter = defaultSubFilter;
        this.subFilters_1 = defaultSubFilter;
        this.subFilters_2 = defaultSubFilter;
        this.subFilters_3 = defaultSubFilter;
    }
}
