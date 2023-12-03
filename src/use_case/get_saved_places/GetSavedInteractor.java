package use_case.get_saved_places;

import java.util.ArrayList;

public class GetSavedInteractor implements GetSavedInputBoundary{

    final GetSavedDataAccessInterface getSavedDataAccessObject;
    final GetSavedOutputBoundary getSavedPresenter;

    public GetSavedInteractor(GetSavedDataAccessInterface getSavedDataAccessObject, GetSavedOutputBoundary getSavedPresenter) {
        this.getSavedDataAccessObject = getSavedDataAccessObject;
        this.getSavedPresenter = getSavedPresenter;
    }

    @Override
    public void execute() {
        ArrayList<String> places = getSavedDataAccessObject.getPlaces();
        GetSavedOutputData getSavedOutputData = new GetSavedOutputData(places);
        getSavedPresenter.prepareView(getSavedOutputData);
    }
}
