package use_case.search;

import java.util.ArrayList;
import entity.Place;

public interface SearchDataAccessInterface {

    boolean isValidFilter(String filter);

    ArrayList<Place> getListing(String city, String filter);
}
