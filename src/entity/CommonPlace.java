package entity;

import java.util.List;

public class CommonPlace implements Place{

    private final String name;
    private final String description;
    private final List<Double> coordinates;
    private final String tags;
    private final Float rating;

    CommonPlace(String name, String description, List<Double> coordinates, String tags, Float rating) {
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.tags = tags;
        this.rating = rating;
    }
    @Override
    public String getName() {return name;}

    @Override
    public String getDescription() {
        return description;
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
    public Float getRating() {
        return rating;
    }
}
