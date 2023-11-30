package use_case.get_saved_places;

import java.util.ArrayList;

public class GetSavedInteractor implements GetSavedInputBoundary{

    final GetSavedDataAccessInterface userDataAccessObject;
    final GetSavedOutputBoundary userPresenter;

    public GetSavedInteractor(GetSavedDataAccessInterface userDataAccessObject, GetSavedOutputBoundary userPresenter) {
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }

    @Override
    public void execute() {
        ArrayList<String> places = userDataAccessObject.getPlaces();
        GetSavedOutputData getSavedOutputData = new GetSavedOutputData(places);
        userPresenter.prepareView(getSavedOutputData);
    }
}
