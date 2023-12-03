package use_case.save_places;

/**
 * The interface to save places as favorite
 */
public interface SavePlacesDataAccessInterface {
    /**
     * The method to save a place as favorite
     * @param name The name of the place being saved
     */
    void save(String name);
}
