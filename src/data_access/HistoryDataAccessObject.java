package data_access;

import use_case.clear_history.ClearDataAccessInterface;
import use_case.get_history.GetHistoryDataAccessInterface;

import java.io.FileNotFoundException;

public class HistoryDataAccessObject implements GetHistoryDataAccessInterface, ClearDataAccessInterface {

    @Override
    public String getHistory() {
        return null;
    }

    @Override
    public void deleteHistory() throws FileNotFoundException {

    }
    public void saveHistory() {}

}
