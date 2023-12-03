package use_case.save_places;

public class SavePlacesInteractor implements SavePlacesInputBoundary {

    final SavePlacesDataAccessInterface savePlacesDataAccessInterface;

    public SavePlacesInteractor(SavePlacesDataAccessInterface savePlacesDataAccessInterface) {
        this.savePlacesDataAccessInterface = savePlacesDataAccessInterface;
    }

    @Override
    public void execute(SavePlacesInputData savePlacesInputData) {
        savePlacesDataAccessInterface.save(savePlacesInputData.getName());
    }
}
