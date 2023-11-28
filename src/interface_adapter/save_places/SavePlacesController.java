package interface_adapter.save_places;

import use_case.save_places.SavePlacesInputBoundary;
import use_case.save_places.SavePlacesInputData;

public class SavePlacesController {

    final SavePlacesInputBoundary saveUseCaseInteractor;

    public SavePlacesController(SavePlacesInputBoundary saveUseCaseInteractor) {
        this.saveUseCaseInteractor = saveUseCaseInteractor;
    }

    public void execute(String name) {
        SavePlacesInputData savePlacesInputData = new SavePlacesInputData(name);
        saveUseCaseInteractor.execute(savePlacesInputData);
    }
}
