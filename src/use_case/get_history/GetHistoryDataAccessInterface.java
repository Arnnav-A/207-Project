package use_case.get_history;

import java.util.ArrayList;

/**
 * Interface to get saved history
 */
public interface GetHistoryDataAccessInterface {
    /**
     * Method to get all saved history
     * @return An array list of history data, which itself is an array list. Each history data, if not empty, contains
     * 3 strings first is the timestamp of the search, followed by the city, followed by the filter being searched for.
     */
    ArrayList<ArrayList<String>> getHistory();
}
