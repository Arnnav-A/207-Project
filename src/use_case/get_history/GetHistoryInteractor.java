package use_case.get_history;

import java.io.FileNotFoundException;

public class GetHistoryInteractor implements GetHistoryInputBoundary {
    final GetHistoryDataAccessInterface userDataAccessObject;
    final GetHistoryOutputBoundary userPresenter;

    public GetHistoryInteractor(GetHistoryDataAccessInterface userDataAccessObject, GetHistoryOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute() throws FileNotFoundException {
        String userHistory = userDataAccessObject.getHistory();
        GetHistoryOutputData getHistoryOutputData = new GetHistoryOutputData(userHistory);
        userPresenter.prepareview(getHistoryOutputData);

    }
}
