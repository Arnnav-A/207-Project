package interface_adapter.place_info;

import use_case.place_info.PlaceInfoInputBoundary;
import use_case.place_info.PlaceInfoInputData;

public class PlaceInfoController {

    final PlaceInfoInputBoundary placeInfoUseCaseInteractor;

    public PlaceInfoController(PlaceInfoInputBoundary placeInfoUseCaseInteractor) {
        this.placeInfoUseCaseInteractor = placeInfoUseCaseInteractor;
    }

    public void execute(String placeName) {
        PlaceInfoInputData placeInfoInputData = new PlaceInfoInputData(placeName);
        placeInfoUseCaseInteractor.execute(placeInfoInputData);
    }
}
