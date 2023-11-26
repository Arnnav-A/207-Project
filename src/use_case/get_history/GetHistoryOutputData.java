package use_case.get_history;

import javax.swing.*;
import java.util.ArrayList;

public class GetHistoryOutputData {
    private ArrayList<ArrayList<String>> userHistory;
    public GetHistoryOutputData(ArrayList<ArrayList<String>> history) {
        this.userHistory = history;
    }
    public ArrayList<ArrayList<String>> getUserHistory() {
        return this.userHistory;
    }
}
