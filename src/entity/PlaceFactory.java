package entity;

import java.util.List;

/**
 * Interface to create place entities
 */
public interface PlaceFactory {
    /**
     * Method to create a place entity with the given details
     * @param name The name of the place
     * @param address The address of the place
     * @param coordinates The coordinates of the place
     * @param tags The tags of the place
     * @param city The city/region the place is in
     * @return The created place entity
     */
    Place create(String name, String address, List<Double> coordinates, String tags, String city);
}
