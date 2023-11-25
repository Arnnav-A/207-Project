package use_case.clear_history;

import java.io.FileNotFoundException;

public interface ClearDataAccessInterface {
    void clearHistory() throws FileNotFoundException;
}
