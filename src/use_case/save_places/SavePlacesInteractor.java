package use_case.save_places;

public class SavePlacesInteractor implements SavePlacesInputBoundary {

    final SavePlacesDataAccessObject savePlacesDataAccessObject;

    public SavePlacesInteractor(SavePlacesDataAccessObject savePlacesDataAccessObject) {
        this.savePlacesDataAccessObject = savePlacesDataAccessObject;
    }

    @Override
    public void execute(SavePlacesInputData savePlacesInputData) {
        savePlacesDataAccessObject.save(savePlacesInputData.getName());
    }
}
