package interface_adapter.clear_history;

import use_case.clear_history.ClearHistoryInputBoundary;


public class ClearHistoryController {
    final ClearHistoryInputBoundary historyClearUseCaseInteractor;

    public ClearHistoryController(ClearHistoryInputBoundary historyClearUseCaseInteractor) {
        this.historyClearUseCaseInteractor = historyClearUseCaseInteractor;
    }
    public void execute(){
        historyClearUseCaseInteractor.execute();
    }
}
