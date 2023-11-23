package interface_adapter.save_history;

import use_case.save_history.SaveInputBoundary;
import use_case.save_history.SaveInputData;

public class SaveController {
    final SaveInputBoundary saveUseCaseInteractor;

    public SaveController(SaveInputBoundary saveUseCaseInteractor) {
        this.saveUseCaseInteractor = saveUseCaseInteractor;
    }
    public void execute(String city, String filter) {
        SaveInputData saveInputData = new SaveInputData(city, filter);
        saveUseCaseInteractor.execute(saveInputData);
    }
}
