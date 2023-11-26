package use_case.place_info;

import java.util.List;

public class PlaceInfoOutputData {

    private final String name;
    private final String address;
    private final String coordinates;
    private final String tags;
    private final String city;

    public PlaceInfoOutputData(String name, String address, String coordinates, String tags, String city) {
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
        this.tags = tags;
        this.city = city;
    }

    public String getName() {
        return name;
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
}