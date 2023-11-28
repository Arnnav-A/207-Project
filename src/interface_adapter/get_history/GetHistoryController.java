package interface_adapter.get_history;

import use_case.get_history.GetHistoryInputBoundary;


public class GetHistoryController {
    final GetHistoryInputBoundary getHistoryUseCaseInteractor;

    public GetHistoryController(GetHistoryInputBoundary getHistoryUseCaseInteractor) {
        this.getHistoryUseCaseInteractor = getHistoryUseCaseInteractor;
    }

    public void execute(){
        getHistoryUseCaseInteractor.execute();
    }
}
