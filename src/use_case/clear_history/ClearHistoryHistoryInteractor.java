package use_case.clear_history;

public class ClearHistoryHistoryInteractor implements ClearHistoryInputBoundary {
    final ClearHistoryDataAccessInterface userDataAccessObject;
    final ClearHistoryOutputBoundary userPresenter;

    public ClearHistoryHistoryInteractor(ClearHistoryDataAccessInterface getUserDataAccessObject, ClearHistoryOutputBoundary userPresenter) {
        this.userDataAccessObject = getUserDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute(){
        ClearHistoryOutputData clearHistoryOutputData = new ClearHistoryOutputData();
        userDataAccessObject.clearHistory();
        userPresenter.prepareView(clearHistoryOutputData);
    }
}
