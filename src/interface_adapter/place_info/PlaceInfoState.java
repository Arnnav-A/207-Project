package interface_adapter.place_info;

public class PlaceInfoState {

    private String placeName;
    private String address;
    private String coordinates;
    private String tags;
    private String city;

    public PlaceInfoState(PlaceInfoState copy) {
        this.placeName = copy.placeName;
        this.address = copy.address;
        this.coordinates = copy.coordinates;
        this.tags = copy.tags;
        this.city = copy.city;
    }

    public PlaceInfoState() {}

    public String getPlaceName() {
        return placeName;
    }

    public String getAddress() {
        return address;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getTags() {
        return tags;
    }

    public String getCity() {
        return city;
    }

    public void setPlaceName(String place_name) {
        this.placeName = place_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
