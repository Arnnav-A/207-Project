package use_case.getFilter;

import entity.CommonFilter;
import entity.Filter;

import java.util.ArrayList;


public class GetFilterOutputData {

    private final Filter allFilters;
    private final ArrayList<String> parentFilters;

    public GetFilterOutputData(Filter superFilter) {
        this.allFilters = superFilter;
        this.parentFilters = superFilter.getSubFilterNames();
    }

    public ArrayList<String> getParentFilters() {
        return parentFilters;
    }

    public Filter getAllFilters() {
        return allFilters;
    }
}

