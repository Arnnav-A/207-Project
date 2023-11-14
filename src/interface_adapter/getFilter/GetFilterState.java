package interface_adapter.getFilter;

import java.util.ArrayList;

public class GetFilterState {
    private String[] parentFilter = {"Please select a Filter"};
    private String[] subFilter_1 = {"Please select a Filter"};
    private String[] subFilter_2 = {"Please select a Filter"};
    private String[] subFilter_3 = {"Please select a Filter"};
    private String selectedParentFilter;
    private String selectedSubFilter1;
    private String selectedSubFilter2;
    private String selectedSubFilter3;

    public GetFilterState() {}

    public String[] getParentFilter() {
        return parentFilter;
    }

    public String[] getSubFilter_1() {
        return subFilter_1;
    }

    public String[] getSubFilter_2() {
        return subFilter_2;
    }

    public String[] getSubFilter_3() {
        return subFilter_3;
    }

    public String getSelectedParentFilter() {
        return selectedParentFilter;
    }

    public String getSelectedSubFilter1() {
        return selectedSubFilter1;
    }

    public String getSelectedSubFilter2() {
        return selectedSubFilter2;
    }

    public String getSelectedSubFilter3() {
        return selectedSubFilter3;
    }

    public void setParentFilter(ArrayList<String> parentFilter) {
        parentFilter.add(0, "Please select a Filter");
        this.parentFilter = parentFilter.toArray(new String[0]);
    }

    public void setSubFilter1(String[] subFilter) {
        this.subFilter_1 = subFilter;
    }

    public void setSubFilter2(ArrayList<String> subFilter) {
        subFilter.add(0, "Please select a Filter");
        this.subFilter_2 = subFilter.toArray(new String[0]);
    }

    public void setSubFilter3(ArrayList<String> subFilter) {
        subFilter.add(0, "Please select a Filter");
        this.subFilter_3 = subFilter.toArray(new String[0]);
    }

    public void setSelectedParentFilter(String parentFilter) {
        this.selectedParentFilter = parentFilter;
    }

    public void setSelectedSubFilter1SubFilter_1(String subFilter) {
        this.selectedSubFilter1 = subFilter;
    }

    public void setSelectedSubFilter2SubFilter_2(String subFilter) {
        this.selectedSubFilter2 = subFilter;
    }

    public void setSelectedSubFilter3SubFilter_3(String subFilter) {
        this.selectedSubFilter3 = subFilter;
    }
}
