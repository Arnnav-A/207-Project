import data_access.FileHistoryDataAccessObject;
import org.junit.Before;
import org.junit.Test;
import use_case.clear_history.ClearHistoryDataAccessInterface;
import use_case.clear_history.ClearHistoryInteractor;
import use_case.clear_history.ClearHistoryOutputBoundary;
import use_case.clear_history.ClearHistoryOutputData;
import use_case.get_history.GetHistoryDataAccessInterface;
import use_case.get_history.GetHistoryInteractor;
import use_case.get_history.GetHistoryOutputBoundary;
import use_case.get_history.GetHistoryOutputData;
import use_case.save_history.SaveDataAccessInterface;
import use_case.save_history.SaveInputData;
import use_case.save_history.SaveInteractor;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static org.junit.Assert.*;
import java.io.File;
public class HistoryTest {
    private GetHistoryDataAccessInterface getHistoryDataAccessObject;
    @Before
    public void init() throws FileNotFoundException {
        getHistoryDataAccessObject = new FileHistoryDataAccessObject("history.csv");
        PrintWriter writer = new PrintWriter("history.csv");
        writer.print("");
        writer.close();
    }
    @Test
    public void emptyHistoryTest() {
        GetHistoryOutputBoundary testPresenter = new GetHistoryOutputBoundary() {
            @Override
            public void prepareview(GetHistoryOutputData response) {
                ArrayList<ArrayList<String>> emptyHistory = new ArrayList<>();
                assertEquals(response.getUserHistory(), emptyHistory);
            }
        };
        GetHistoryInteractor getHistoryInteractor = new GetHistoryInteractor(getHistoryDataAccessObject, testPresenter);
        getHistoryInteractor.execute();
    }

    @Test
    public void successGetTest() throws FileNotFoundException {
        GetHistoryOutputBoundary testPresenter = new GetHistoryOutputBoundary() {
            @Override
            public void prepareview(GetHistoryOutputData response) {
                assertFalse(response.getUserHistory().isEmpty());
                assertEquals(1, response.getUserHistory().size());
                assertNotNull(response.getUserHistory().get(0).get(0));
                assertEquals("Toronto", response.getUserHistory().get(0).get(1));
                assertEquals("food", response.getUserHistory().get(0).get(2));
            }
        };
        SaveDataAccessInterface saveDataAccessObject = new FileHistoryDataAccessObject("history.csv");
        SaveInteractor saveInteractor = new SaveInteractor(saveDataAccessObject);
        SaveInputData saveInputData = new SaveInputData("Toronto", "food");
        saveInteractor.execute(saveInputData);
        GetHistoryInteractor getHistoryInteractor = new GetHistoryInteractor(getHistoryDataAccessObject, testPresenter);
        getHistoryInteractor.execute();
        PrintWriter writer = new PrintWriter("history.csv");
        writer.print("");
        writer.close();
    }

    @Test
    public void clearTest() {
        SaveDataAccessInterface saveDataAccessObject = new FileHistoryDataAccessObject("history.csv");
        ClearHistoryDataAccessInterface clearHistoryDataAccessObject = new FileHistoryDataAccessObject("history.csv");
        SaveInteractor saveInteractor = new SaveInteractor(saveDataAccessObject);
        SaveInputData saveInputData = new SaveInputData("Toronto", "food");
        saveInteractor.execute(saveInputData);
        File file = new File("history.csv");
        ClearHistoryOutputBoundary clearPresenter = new ClearHistoryOutputBoundary() {
            @Override
            public void prepareView(ClearHistoryOutputData response) {
                assertEquals(response.message, "User history is deleted.");
                assertEquals(0, file.length());
            }
        };
        ClearHistoryInteractor clearHistoryInteractor = new ClearHistoryInteractor(clearHistoryDataAccessObject, clearPresenter);
        clearHistoryInteractor.execute();
    }

}
