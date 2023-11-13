package entity;

import java.util.ArrayList;

public class CommonFilter implements Filter {

    private final String name;
    private final ArrayList<CommonFilter> subfilters;

    public CommonFilter(String name) {
        this.name = name;
        this.subfilters = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CommonFilter getSubFilter(String name) {
        CommonFilter selectedFilter = null;
        for (CommonFilter filter : subfilters) {
            if (filter.getName().equals(name)) {
                selectedFilter = filter;
            }
        }
        return selectedFilter;
    }


    @Override
    public ArrayList<String> getSubFilterNames() {
        ArrayList<String> result = new ArrayList<>();
        for (CommonFilter filter : subfilters) {
            result.add(filter.getName());
        }
        return result;
    }

    @Override
    public void setSubFilter(CommonFilter subFilter) {
        subfilters.add(subFilter);
    }
}
