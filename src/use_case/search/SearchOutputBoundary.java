package use_case.search;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData listing);

    void prepareFailView(String error);
}
