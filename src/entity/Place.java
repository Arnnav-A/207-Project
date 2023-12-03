package entity;

import java.util.List;

/**
 * Interface for a place entity
 */
public interface Place {

    /**
     * Method to get the name of the place
     * @return The name of the place
     */
    String getName();

    /**
     * Method to get the address of the place
     * @return The address of the place
     */
    String getAddress();

    /**
     * Method to get the coordinates of the place
     * @return The coordinates of the place
     */
    List<Double> getCoordinates();

    /**
     * Method to get the tags of the place
     * @return The tags of the place
     */
    String getTags();

    /**
     * Method to get the city of the place
     * @return The city of the place
     */
    String getCity();
}
