package interface_adapter.clear;

import use_case.clear_history.ClearInputBoundary;


public class ClearController {
    final ClearInputBoundary historyClearUseCaseInteractor;

    public ClearController(ClearInputBoundary historyClearUseCaseInteractor) {
        this.historyClearUseCaseInteractor = historyClearUseCaseInteractor;
    }
    public void execute(){
        historyClearUseCaseInteractor.execute();
    }
}
