package entity;

import java.util.List;

public class CommonPlace implements Place{

    private final String name;
    private final String address;
    private final List<Double> coordinates;
    private final String tags;
    private final String city;

    CommonPlace(String name, String address, List<Double> coordinates, String tags, String city) {
        this.name = name;
        this.address = address;
        this.coordinates = coordinates;
        this.tags = tags;
        this.city = city;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public List<Double> getCoordinates() {
        return coordinates;
    }

    @Override
    public String getTags() {
        return tags;
    }

    @Override
    public String getCity() {
        return city;
    }
}
