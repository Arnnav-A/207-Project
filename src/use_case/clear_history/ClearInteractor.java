package use_case.clear_history;

import java.io.FileNotFoundException;

public class ClearInteractor implements ClearInputBoundary {
    final ClearDataAccessInterface userDataAccessObject;
    final ClearOutputBoundary userPresenter;

    public ClearInteractor(ClearDataAccessInterface getUserDataAccessObject, ClearOutputBoundary userPresenter) {
        this.userDataAccessObject = getUserDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute() throws FileNotFoundException {
        ClearOutputData clearOutputData = new ClearOutputData();
        userDataAccessObject.deleteHistory();
        userPresenter.prepareView(clearOutputData);
    }
}
