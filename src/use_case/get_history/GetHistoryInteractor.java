package use_case.get_history;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GetHistoryInteractor implements GetHistoryInputBoundary {
    final GetHistoryDataAccessInterface userDataAccessObject;
    final GetHistoryOutputBoundary userPresenter;

    public GetHistoryInteractor(GetHistoryDataAccessInterface userDataAccessObject, GetHistoryOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute() throws FileNotFoundException {
        ArrayList<ArrayList<String>> userHistory = userDataAccessObject.getHistory();
        GetHistoryOutputData getHistoryOutputData = new GetHistoryOutputData(userHistory);
        userPresenter.prepareview(getHistoryOutputData);

    }
}
