package use_case.search;

public class SearchInputData {

    private final String city;
    private final String filter;


    public SearchInputData(String city, String filter) {
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
