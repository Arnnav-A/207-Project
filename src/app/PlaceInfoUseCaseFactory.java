package app;

import data_access.FileSavePlacesDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.place_info.PlaceInfoViewModel;
import interface_adapter.save_places.SavePlacesController;
import use_case.save_places.SavePlacesInputBoundary;
import use_case.save_places.SavePlacesInteractor;
import view.PlaceInfoView;

public class PlaceInfoUseCaseFactory {

    private PlaceInfoUseCaseFactory() {}

    public static PlaceInfoView create(PlaceInfoViewModel placeInfoViewModel,
                                       ViewManagerModel viewManagerModel,
                                       FileSavePlacesDataAccessObject savePlacesDataAccessObject) {
        SavePlacesController savePlacesController = createSavePlacesUseCase(savePlacesDataAccessObject);
        return new PlaceInfoView(placeInfoViewModel, viewManagerModel, savePlacesController);

    }

    private static SavePlacesController createSavePlacesUseCase(FileSavePlacesDataAccessObject savePlacesDataAccessObject) {
        SavePlacesInputBoundary savePlaceInteractor = new SavePlacesInteractor(savePlacesDataAccessObject);
        return new SavePlacesController(savePlaceInteractor);
    }
}
