import entity.CommonPlaceFactory;
import entity.Place;
import entity.PlaceFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.getFilter.GetFilterPresenter;
import interface_adapter.getFilter.GetFilterViewModel;
import use_case.getFilter.GetFilterInputBoundary;
import use_case.getFilter.GetFilterInteractor;
import use_case.getFilter.GetFilterOutputBoundary;
import use_case.search.SearchDataAccessInterface;
import java.util.ArrayList;


public class GetFilterInteractorTest {

    public class FakeDAO implements SearchDataAccessInterface {

        @Override
        public boolean isValidFilter(String filter) {
            return false;
        }

        @Override
        public boolean isValidCity(String city) {
            return false;
        }

        @Override
        public ArrayList<Place> getListing(String city, String filter) {
            return null;
        }

        @Override
        public ArrayList<String> getAllFilters() {
            ArrayList<String> result = new ArrayList<>();
            result.add("accommodation");
            result.add("accommodation.hotel");
            result.add("accommodation.apartment");
            result.add("accommodation.chalet");
            result.add("activity");
            return result;
        }
    }
    @org.junit.Test
    public void testInteractor() {
        PlaceFactory placeFactory = new CommonPlaceFactory();
        SearchDataAccessInterface DAO = new FakeDAO();
        GetFilterOutputBoundary presenter = new GetFilterPresenter(new GetFilterViewModel(), new ViewManagerModel());
        GetFilterInputBoundary interactor = new GetFilterInteractor(DAO, presenter);
        interactor.execute();
    }
}
