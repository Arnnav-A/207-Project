package use_case.get_saved_places;

import java.util.ArrayList;

/**
 * Interface to get saved places
 */
public interface GetSavedDataAccessInterface {

    /**
     * Method to get saved places
     * @return An array list of names of places saved
     */
    ArrayList<String> getPlaces();
}
