package use_case.get_history;

import java.io.FileNotFoundException;

public interface GetHistoryInputBoundary {
    void execute() throws FileNotFoundException;
}
