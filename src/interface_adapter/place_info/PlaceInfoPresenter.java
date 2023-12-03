package interface_adapter.place_info;

import interface_adapter.ViewManagerModel;
import use_case.place_info.PlaceInfoOutputBoundary;
import use_case.place_info.PlaceInfoOutputData;

public class PlaceInfoPresenter implements PlaceInfoOutputBoundary {

    private final PlaceInfoViewModel placeInfoViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlaceInfoPresenter(PlaceInfoViewModel placeInfoViewModel, ViewManagerModel viewManagerModel) {
        this.placeInfoViewModel = placeInfoViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareView(PlaceInfoOutputData placeInfoOutputData) {
        PlaceInfoState placeInfoState = this.placeInfoViewModel.getState();
        placeInfoState.setPlaceName(placeInfoOutputData.getName());
        placeInfoState.setAddress(placeInfoOutputData.getAddress());
        placeInfoState.setCoordinates(placeInfoOutputData.getCoordinates());
        placeInfoState.setTags(placeInfoOutputData.getTags());
        placeInfoState.setCity(placeInfoOutputData.getCity());
        this.placeInfoViewModel.setState(placeInfoState);
        this.placeInfoViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(placeInfoViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
