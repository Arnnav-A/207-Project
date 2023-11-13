package use_case.getFilter;

import entity.CommonListing;
import use_case.search.SearchDataAccessInterface;
import entity.CommonFilter;
import java.util.*;

public class GetFilterInteractor implements GetFilterInputBoundary {
    final SearchDataAccessInterface userDataAccessObject;

    final GetFilterOutputBpundary getFilterPresenter;

    public GetFilterInteractor(SearchDataAccessInterface userDataAccessObject,
                               GetFilterOutputBpundary getFilterPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.getFilterPresenter = getFilterPresenter;
    }


    @Override
    public void execute() {
        ArrayList<String> filters = userDataAccessObject.getAllFilters();
        GetFilterOutputData getFilterOutputData = new GetFilterOutputData(createFilter(filters));
        getFilterPresenter.prepareView(getFilterOutputData);

    }


    private static CommonFilter createFilter(ArrayList<String> originalList) {
        CommonFilter parentFilter = new CommonFilter("All Filters");
        for (String option : originalList) {
            String[] segments = option.split("\\.");
            CommonFilter subFilter = parentFilter;
            for (String segment : segments) {
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

