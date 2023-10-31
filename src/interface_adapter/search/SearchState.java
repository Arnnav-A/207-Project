package interface_adapter.search;

public class SearchState {
    private String cityname = "";
    private String citynameError = null;

    public SearchState() {
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCitynameError() {
        return citynameError;
    }

    public void setCitynameError(String citynameError) {
        this.citynameError = citynameError;
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

    private String filter = "";
    private String filterError = null;


}
