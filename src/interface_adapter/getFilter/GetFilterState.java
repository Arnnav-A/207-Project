package interface_adapter.getFilter;

import java.util.ArrayList;

public class GetFilterState {
    private String[] parentFilter = {"Please select a Filter"};
    private String[] subFilter_1 = {"Please select a Filter"};
    private String[] subFilter_2 = {"Please select a Filter"};
    private String[] subFilter_3 = {"Please select a Filter"};

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

    public void setParentFilter(ArrayList<String> parentFilter) {
        parentFilter.add(0, "Please select a Filter");
        this.parentFilter = parentFilter.toArray(new String[0]);
    }

    public void setSubFilter1(ArrayList<String> subFilter) {
        subFilter.add(0, "Please select a Filter");
        this.subFilter_1 = subFilter.toArray(new String[0]);
    }

    public void setSubFilter2(ArrayList<String> subFilter) {
        subFilter.add(0, "Please select a Filter");
        this.subFilter_2 = subFilter.toArray(new String[0]);
    }

    public void setSubFilter3(ArrayList<String> subFilter) {
        subFilter.add(0, "Please select a Filter");
        this.subFilter_3 = subFilter.toArray(new String[0]);
    }
}
