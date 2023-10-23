package entity;

import java.util.List;

public class CommonPlaceFactory implements PlaceFactory {
    @Override
    public Place create(String name, String description, List<Double> coordinates, String tags, Float rating) {
        return new CommonPlace(name, description, coordinates, tags, rating);
    }
}
