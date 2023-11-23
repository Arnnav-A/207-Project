package use_case.get_history;

import javax.swing.*;

public class GetHistoryOutputData {
    private String userHistory;
    public GetHistoryOutputData(String history) {
        this.userHistory = history;
    }
    public void message() {
        JOptionPane.showMessageDialog(null, userHistory);
    }
}
