package use_case.save_history;

import java.time.LocalDateTime;

public interface SaveDataAccessInterface {
    void save(LocalDateTime now, String city, String filter);
}
