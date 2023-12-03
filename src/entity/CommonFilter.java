package entity;

import java.util.ArrayList;

public class CommonFilter implements Filter {

    private final String name;
    private final ArrayList<Filter> subfilters;

    public CommonFilter(String name) {
        this.name = name;
        this.subfilters = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Filter getSubFilter(String name) {
        Filter selectedFilter = new CommonFilter("");
        for (Filter filter : subfilters) {
            if (filter.getName().equals(name)) {
                selectedFilter = filter;
            }
        }
        return selectedFilter;
    }


    @Override
    public ArrayList<String> getSubFilterNames() {
        ArrayList<String> result = new ArrayList<>();
        for (Filter filter : subfilters) {
            result.add(filter.getName());
        }
        return result;
    }

    @Override
    public void setSubFilter(Filter subFilter) {
        subfilters.add(subFilter);
    }
}
