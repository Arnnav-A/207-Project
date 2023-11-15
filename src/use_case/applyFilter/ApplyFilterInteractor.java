package use_case.applyFilter;

import use_case.getFilter.GetFilterOutputBoundary;

public class ApplyFilterInteractor implements ApplyFilterInputBoundary{
    final GetFilterOutputBoundary getFilterPresenter;

    public ApplyFilterInteractor(GetFilterOutputBoundary getFilterPresenter) {
        this.getFilterPresenter = getFilterPresenter;
    }


    @Override
    public void execute() {

    }
}
