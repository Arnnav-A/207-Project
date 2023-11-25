package use_case.get_history;
import java.util.ArrayList;

public class GetHistoryInteractor implements GetHistoryInputBoundary {
    final GetHistoryDataAccessInterface userDataAccessObject;
    final GetHistoryOutputBoundary userPresenter;

    public GetHistoryInteractor(GetHistoryDataAccessInterface userDataAccessObject, GetHistoryOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(){
        ArrayList<ArrayList<String>> userHistory = userDataAccessObject.getHistory();
        StringBuilder formattedHistory = new StringBuilder();
        for (ArrayList<String> history:userHistory) {
            formattedHistory.append(history.toString()).append("\n");
        }
        String history = formattedHistory.toString();
        GetHistoryOutputData getHistoryOutputData = new GetHistoryOutputData(history);
        userPresenter.prepareview(getHistoryOutputData);

    }
}
