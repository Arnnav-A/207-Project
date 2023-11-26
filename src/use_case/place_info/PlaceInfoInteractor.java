package use_case.place_info;

import entity.Place;

public class PlaceInfoInteractor implements PlaceInfoInputBoundary{

    final PlaceInfoDataAccessInterface placeInfoDataAccessObject;
    final PlaceInfoOutputBoundary placeInfoPresenter;

    public PlaceInfoInteractor(PlaceInfoDataAccessInterface placeInfoDataAccessObject, PlaceInfoOutputBoundary placeInfoPresenter) {
        this.placeInfoDataAccessObject = placeInfoDataAccessObject;
        this.placeInfoPresenter = placeInfoPresenter;
    }

    @Override
    public void execute(PlaceInfoInputData placeInfoInputData) {
        String placeName = placeInfoInputData.getPlaceName();
        Place place = placeInfoDataAccessObject.getPlaceFromName(placeName);
        PlaceInfoOutputData placeInfoOutputData = new PlaceInfoOutputData(place.getName(), place.getAddress(),
                place.getCoordinates().toString(), place.getTags(), place.getCity());
        placeInfoPresenter.prepareView(placeInfoOutputData);
    }
}
