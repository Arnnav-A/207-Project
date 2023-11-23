package use_case.save_history;

public class SaveInputData {
    private final String city;
    private final String filter;

    public SaveInputData(String city, String filter) {
        this.city = city;
        this.filter = filter;
    }

    public String getCity() {
        return city;
    }

    public String getFilter() {
        return filter;
    }
}
