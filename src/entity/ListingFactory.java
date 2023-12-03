package entity;


import java.util.ArrayList;

/**
 * Interface to create a listing entity
 */
public interface ListingFactory {

    /**
     * Method to create a listing entity
     * @param points The places being stored in the listing
     * @param city The city used in search
     * @param filter The filter used in search
     * @return The created listing entity
     */
    Listing create(ArrayList<Place> points, String city, String filter);
}
