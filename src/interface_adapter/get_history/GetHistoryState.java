package interface_adapter.get_history;

import java.util.ArrayList;

public class GetHistoryState {

    private ArrayList<ArrayList<String>> history = new ArrayList<>();

    public GetHistoryState() {
    }

    public ArrayList<ArrayList<String>> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<ArrayList<String>> history) {
        this.history = history;
    }
}
