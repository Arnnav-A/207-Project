package entity;

import java.util.List;

/**
 * Interface for representing a search result
 */
public interface Listing {

    /**
     * Method to get all place entities returned
     * @return Array list of places returned
     */
    List<Place> getPoints();

    /**
     * Method to get the city searched
     * @return The city used in search
     */
    String getCity();

    /**
     * Method to get the filter searched
     * @return The filter used in search
     */
    String getFilter();
}
