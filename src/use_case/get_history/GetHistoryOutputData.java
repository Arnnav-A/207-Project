package use_case.get_history;

import javax.swing.*;
import java.util.ArrayList;

public class GetHistoryOutputData {
    private String userHistory;
    public GetHistoryOutputData(String history) {
        this.userHistory = history;
    }
    public void message() {
        JOptionPane.showMessageDialog(null, userHistory);
    }
}
