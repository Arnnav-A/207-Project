package interface_adapter.getFilter;

import use_case.getFilter.GetFilterInputBoundary;

public class GetFilterController {

    final GetFilterInputBoundary userGetFilterUseCaseInteractor;

    public GetFilterController(GetFilterInputBoundary userGetFilterUseCaseInteractor) {
        this.userGetFilterUseCaseInteractor = userGetFilterUseCaseInteractor;
    }

    public void execute() {
        userGetFilterUseCaseInteractor.execute();
    }
}
