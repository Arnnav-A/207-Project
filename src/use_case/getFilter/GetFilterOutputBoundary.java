package use_case.getFilter;

public interface GetFilterOutputBoundary {
    void prepareView(GetFilterOutputData filterOutputData);

    void prepareFailView(String error);
}
