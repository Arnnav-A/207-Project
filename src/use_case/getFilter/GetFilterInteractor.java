package use_case.getFilter;

import entity.Filter;
import use_case.search.SearchDataAccessInterface;
import entity.CommonFilter;
import java.util.*;

public class GetFilterInteractor implements GetFilterInputBoundary {
    final SearchDataAccessInterface userDataAccessObject;

    final GetFilterOutputBoundary getFilterPresenter;

    public GetFilterInteractor(SearchDataAccessInterface userDataAccessObject,
                               GetFilterOutputBoundary getFilterPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.getFilterPresenter = getFilterPresenter;
    }


    @Override
    public void execute() {
        ArrayList<String> filters = userDataAccessObject.getAllFilters();
        GetFilterOutputData getFilterOutputData = new GetFilterOutputData(createFilter(filters));
        getFilterPresenter.prepareView(getFilterOutputData);

    }


    private static Filter createFilter(ArrayList<String> originalList) {
        Filter parentFilter = new CommonFilter("All Filters");
        for (String option : originalList) {
            String[] segments = option.split("\\.");
            Filter subFilter = parentFilter;
            for (String segment : segments) {
                segment = segment.replace("_", " ");
                if (!subFilter.getSubFilterNames().contains(segment)) {
                    subFilter.setSubFilter(new CommonFilter(segment));
                    subFilter = subFilter.getSubFilter(segment);
                } else {
                    subFilter = subFilter.getSubFilter(segment);
                }
            }
        }
        return parentFilter;
    }
}

