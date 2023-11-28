package interface_adapter.search;

public class SearchState {

    private String city_name = "";
    private String error = null;
    private String filter = "";
    private String notice = null;

    public SearchState(SearchState copy) {
        city_name = copy.city_name;
        error = copy.error;
        filter = copy.filter;
        notice = copy.notice;
    }

    public SearchState() {}
    // getter and setter of the four variables.
    public String getCityName() {
        return city_name;
    }

    public void setCityName(String city_name) {
        this.city_name = city_name;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setNotice(String notice) {this.notice = notice;}

    public String getNotice() {return notice;}

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
