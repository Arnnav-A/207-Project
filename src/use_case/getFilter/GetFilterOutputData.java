package use_case.getFilter;

import entity.CommonFilter;

import java.util.ArrayList;


public class GetFilterOutputData {

    private final CommonFilter allFilters;
    private final ArrayList<String> parentFilters;
    private ArrayList<String> subFilters_1 = new ArrayList<>();
    private ArrayList<String> subFilters_2 = new ArrayList<>();
    private ArrayList<String> subFilters_3 = new ArrayList<>();

    public GetFilterOutputData(CommonFilter superFilter) {
        this.allFilters = superFilter;
        this.parentFilters = superFilter.getSubFilterNames();
    }

    public ArrayList<String> getParentFilters() {
        return parentFilters;
    }

    public ArrayList<String> getSubFilters_1() {
        return subFilters_1;
    }

    public ArrayList<String> getSubFilters_2() {
        return subFilters_2;
    }

    public ArrayList<String> getSubFilters_3() {
        return subFilters_3;
    }

    public CommonFilter getSelectedFilter(String name) {
        return allFilters.getSubFilter(name);
    }

    public void setSubFilters_1(CommonFilter filter) {
        subFilters_1 = filter.getSubFilterNames();
    }

    public void setSubFilters_2(CommonFilter filter) {
        subFilters_2 = filter.getSubFilterNames();
    }

    public void setSubFilters_3(CommonFilter filter) {
        subFilters_3 = filter.getSubFilterNames();
    }
}

