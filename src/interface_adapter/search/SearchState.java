package interface_adapter.search;

public class SearchState {
    private String city_name = "";
    private String cityNameError = null;
    private String filter = "";
    private String filterError = null;

    public SearchState() {}
    // getter and setter of the four variables.
    public String getCityName() {
        return city_name;
    }

    public void setCityName(String city_name) {
        this.city_name = city_name;
    }

    public String getCityNameError() {
        return cityNameError;
    }

    public void setCityNameError(String cityNameError) {
        this.cityNameError = cityNameError;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getFilterError() {
        return filterError;
    }

    public void setFilterError(String filterError) {
        this.filterError = filterError;
    }
}
