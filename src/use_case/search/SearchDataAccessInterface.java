package use_case.search;

import java.util.ArrayList;
import entity.Place;

/**
 * The interface to search for places and available filters
 */
public interface SearchDataAccessInterface {

    /**
     * Checks whether the provided filter is valid. It checks for underscores and periods, otherwise just the first word
     * @param filter the filter being searched for
     * @return true iff the filter is present in the filter.csv file
     */
    boolean isValidFilter(String filter);

    /**
     * Checks whether the provided city can be found using the API
     * @param city the city being searched for
     * @return true iff the city is found by the API
     */
    boolean isValidCity(String city);

    /**
     * Looks for places with the given filter as a tag in the provided city
     * Assumes that isValidCity and isValidFilter return true for these inputs
     * @param city the city being searched in
     * @param filter the filter being searched for
     * @return An ArrayList of Places found by the API
     */
    ArrayList<Place> getListing(String city, String filter);

    /**
     * Gets the all filters present in the filters csv file
     * @return An ArrayList of String where each string is a valid filter
     */
    ArrayList<String> getAllFilters();
}