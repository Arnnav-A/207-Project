import data_access.FilePlaceInfoDataAccessObject;
import data_access.FileSearchDataAccessObject;
import entity.CommonListingFactory;
import entity.CommonPlaceFactory;
import entity.ListingFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.listing_results.ListingResultsViewModel;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.place_info.*;
import use_case.search.*;

import static org.junit.Assert.assertFalse;

public class PlaceInfoInteractorTest {

    private SearchInteractor searchInteractor;

    @Before
    public void init() {
        SearchDataAccessInterface searchDataAccessObject = new FileSearchDataAccessObject(new CommonPlaceFactory(),
                "src/data_access/filters.csv", "listingJSONTest2.json");
        ListingFactory listingFactory = new CommonListingFactory();
        SearchViewModel searchViewModel = new SearchViewModel();
        ListingResultsViewModel listingResultsViewModel = new ListingResultsViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchOutputBoundary searchPresenter = new SearchPresenter(searchViewModel, listingResultsViewModel,
                viewManagerModel);
        this.searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter,
                listingFactory);
        SearchInputData searchInputData = new SearchInputData("Toronto", "entertainment.museum");
        searchInteractor.execute(searchInputData);
    }

    @Test
    public void SuccessPlaceInfoTest() {

        PlaceInfoDataAccessInterface placeInfoDataAccessObject = new
                FilePlaceInfoDataAccessObject("listingJSONTest2.json", new CommonPlaceFactory());
        PlaceInfoOutputBoundary placeInfoPresenter = new PlaceInfoOutputBoundary() {
            @Override
            public void prepareView(PlaceInfoOutputData placeInfoOutputData) {
                assert(placeInfoOutputData.getName().equals("Royal Ontario Museum"));
                assert(placeInfoOutputData.getAddress().equals("100 Queen's Park, Old Toronto, ON M5S 2C6, Canada"));
                assert(placeInfoOutputData.getCity().equals("Old Toronto"));
                assertFalse(placeInfoOutputData.getCoordinates().isEmpty());
                assertFalse(placeInfoOutputData.getTags().isEmpty());
            }
        };
        PlaceInfoInputData placeInfoInputData = new PlaceInfoInputData("Royal Ontario Museum");
        PlaceInfoInteractor placeInfoInteractor = new PlaceInfoInteractor(placeInfoDataAccessObject,
                placeInfoPresenter);
        placeInfoInteractor.execute(placeInfoInputData);
    }

    @Test
    public void NoInfoPlaceInfoTest() {
        SearchInputData searchInputData = new SearchInputData("Toronto", "entertainment.museum");
        searchInteractor.execute(searchInputData);
        PlaceInfoDataAccessInterface placeInfoDataAccessObject = new
                FilePlaceInfoDataAccessObject("listingJSONTest2.json", new CommonPlaceFactory());
        PlaceInfoOutputBoundary placeInfoPresenter = new PlaceInfoOutputBoundary() {
            @Override
            public void prepareView(PlaceInfoOutputData placeInfoOutputData) {
                assert(placeInfoOutputData.getName().equals("Place Details Not Found"));
                assert(placeInfoOutputData.getAddress().isEmpty());
                assert(placeInfoOutputData.getCity().isEmpty());
                assert(placeInfoOutputData.getCoordinates().equals("[]"));
                assert(placeInfoOutputData.getTags().isEmpty());
            }
        };
        PlaceInfoInputData placeInfoInputData = new PlaceInfoInputData("University of Toronto");
        PlaceInfoInteractor placeInfoInteractor = new PlaceInfoInteractor(placeInfoDataAccessObject,
                placeInfoPresenter);
        placeInfoInteractor.execute(placeInfoInputData);
    }
}
