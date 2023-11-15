package interface_adapter.applyFilter;

import use_case.applyFilter.ApplyFilterInputBoundary;

public class ApplyFilterController {

    final ApplyFilterInputBoundary applyFilterUseCaseInteractor;

    public ApplyFilterController(ApplyFilterInputBoundary applyFilterUseCaseInteractor) {
        this.applyFilterUseCaseInteractor = applyFilterUseCaseInteractor;
    }

    public void execute() {
        applyFilterUseCaseInteractor.execute();
    }
}
