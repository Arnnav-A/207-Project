package use_case.save_history;

import java.time.LocalDateTime;

/**
 * Interface to save searched history
 */
public interface SaveDataAccessInterface {
    /**
     * Method to save place
     * @param now The timestamp when the search was made
     * @param city The city used in the search
     * @param filter The filter used in the search
     */
    void save(LocalDateTime now, String city, String filter);
}
