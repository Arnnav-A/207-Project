package use_case.place_info;

import entity.Place;

/**
 * Interface to get a place details from the name of the place
 */
public interface PlaceInfoDataAccessInterface {

    /**
     * Method to get the place entity corresponding to the name of the place provided
     * @param name The name of place being called
     * @return The place entity with the given name, returns first occurrence if multiple
     */
    Place getPlaceFromName(String name);
}
