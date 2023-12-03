import data_access.FileSearchDataAccessObject;
import entity.CommonListingFactory;
import entity.CommonPlaceFactory;
import entity.ListingFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.search.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class SearchInteractorTest {

    private SearchDataAccessInterface searchDataAccessObject;
    private ListingFactory listingFactory;

    @Before
    public void init() {
        this.listingFactory = new CommonListingFactory();
        this.searchDataAccessObject = new FileSearchDataAccessObject(new CommonPlaceFactory(),
                "src/data_access/filters.csv", "listingJSONTest1.json");
    }

    @Test
    public void FailSearchTest1() {
        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                fail("An unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assert(error.equals("Filter not found."));
            }
        };
        SearchInteractor searchInteractor = new SearchInteractor(this.searchDataAccessObject,
                searchPresenter, this.listingFactory);
        SearchInputData searchInputData = new SearchInputData("Toronto", "fake filter");
        searchInteractor.execute(searchInputData);
    }

    @Test
    public void FailSearchTest2() {
        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                fail("An unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assert(error.equals("City not found."));
            }
        };
        SearchInteractor searchInteractor = new SearchInteractor(this.searchDataAccessObject,
                searchPresenter, this.listingFactory);
        SearchInputData searchInputData = new SearchInputData("randomstring", "accommodation");
        searchInteractor.execute(searchInputData);
    }

    @Test
    public void FailSearchTest3() {
        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                fail("An unexpected use case success");
            }

            @Override
            public void prepareFailView(String error) {
                assert(error.equals("Places not found."));
            }
        };
        SearchInteractor searchInteractor = new SearchInteractor(this.searchDataAccessObject,
                searchPresenter, this.listingFactory);
        SearchInputData searchInputData = new SearchInputData("Sao Paulo", "beach");
        searchInteractor.execute(searchInputData);
    }

    @Test
    public void SuccessSearchTest() {
        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {
                assert(searchOutputData.getCity().equals("Toronto"));
                assert(searchOutputData.getFilter().equals("museum"));
                assertFalse(searchOutputData.getPlacesNames().isEmpty());
            }

            @Override
            public void prepareFailView(String error) {
                fail("An unexpected use case failure");
            }
        };
        SearchInteractor searchInteractor = new SearchInteractor(this.searchDataAccessObject,
                searchPresenter, this.listingFactory);
        SearchInputData searchInputData = new SearchInputData("Toronto", "museum");
        searchInteractor.execute(searchInputData);
    }
}
