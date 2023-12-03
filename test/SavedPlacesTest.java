import data_access.FileSearchDataAccessObject;
import entity.CommonListingFactory;
import entity.ListingFactory;
import use_case.get_saved_places.GetSavedDataAccessInterface;
import data_access.FileSavePlacesDataAccessObject;
import entity.CommonPlaceFactory;
import org.junit.Before;
import org.junit.Test;
import use_case.get_saved_places.GetSavedInteractor;
import use_case.get_saved_places.GetSavedOutputBoundary;
import use_case.get_saved_places.GetSavedOutputData;
import use_case.save_places.SavePlacesDataAccessInterface;
import use_case.save_places.SavePlacesInputData;
import use_case.save_places.SavePlacesInteractor;
import use_case.search.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SavedPlacesTest {

    private final String listingJSON = "listingJSONTest.json";
    private final String savedPlaceCSV = "savedCSVTest.csv";
    private final GetSavedDataAccessInterface getSavedDataAccessInterface = new FileSavePlacesDataAccessObject(listingJSON, savedPlaceCSV, new CommonPlaceFactory());

    @Before
    public void init() {
        FileWriter writer;
        try {
            writer = new FileWriter(savedPlaceCSV);
            writer.flush(); // Clearing the saved places file before starting a test
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void EmptySavedTest() {
        GetSavedOutputBoundary userPresenter = new GetSavedOutputBoundary() {
            @Override
            public void prepareView(GetSavedOutputData response) {
                assert response.getPlaces().isEmpty();
            }
        };
        GetSavedInteractor interactor = new GetSavedInteractor(this.getSavedDataAccessInterface, userPresenter);
        interactor.execute();
    }

    @Test
    public void SavePlaceTest() {
        SearchDataAccessInterface searchDataAccessObject = new FileSearchDataAccessObject(new CommonPlaceFactory(),
                "src/data_access/filters.csv", listingJSON);
        ListingFactory listingFactory = new CommonListingFactory();
        SearchOutputBoundary searchPresenter = new SearchOutputBoundary() {
            @Override
            public void prepareSuccessView(SearchOutputData searchOutputData) {}

            @Override
            public void prepareFailView(String error) {}
        };
        SearchInteractor searchInteractor = new SearchInteractor(searchDataAccessObject, searchPresenter,
                listingFactory);
        SearchInputData searchInputData = new SearchInputData("Toronto", "entertainment.museum");
        searchInteractor.execute(searchInputData);
        SavePlacesDataAccessInterface savePlacesDataAccessInterface = new FileSavePlacesDataAccessObject(listingJSON, savedPlaceCSV, new CommonPlaceFactory());
        SavePlacesInteractor interactor = new SavePlacesInteractor(savePlacesDataAccessInterface);
        String museum = "Royal Ontario Museum";
        SavePlacesInputData inputData = new SavePlacesInputData(museum);
        interactor.execute(inputData);
        Scanner scanner;
        try {
            scanner = new Scanner(new File(savedPlaceCSV));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String data = scanner.nextLine();
        String[] dataArray = data.split(",");
        assert dataArray[0].equals(museum);
    }
}
