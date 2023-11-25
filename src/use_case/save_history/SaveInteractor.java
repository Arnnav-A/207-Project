package use_case.save_history;

import java.time.LocalDateTime;

public class SaveInteractor implements SaveInputBoundary{
    final SaveDataAccessInterface saveDataAccessObject;

    public SaveInteractor(SaveDataAccessInterface saveDataAccessInterface) {
        this.saveDataAccessObject = saveDataAccessInterface;
    }

    @Override
    public void execute(SaveInputData saveInputData) {
        String city = saveInputData.getCity();
        String filter = saveInputData.getFilter();
        if ((!city.isEmpty())) {
            if (!filter.isEmpty()) {
                LocalDateTime now = LocalDateTime.now();
                saveDataAccessObject.save(now, city, filter);
            }
        }
    }
}
