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

    public CommonFilter getAllFilters() {
        return allFilters;
    }
}

