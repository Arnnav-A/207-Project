package interface_adapter.get_saved_places;

import use_case.get_saved_places.GetSavedInputBoundary;

public class GetSavedController {

    final GetSavedInputBoundary getSavedUseCaseInteractor;

    public GetSavedController(GetSavedInputBoundary getSavedUseCaseInteractor) {
        this.getSavedUseCaseInteractor = getSavedUseCaseInteractor;
    }

    public void execute() {
        getSavedUseCaseInteractor.execute();
    }
}
