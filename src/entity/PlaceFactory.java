package entity;

import java.util.List;

public interface PlaceFactory {
    Place create(String name, String description, List<Double> coordinates, String tags, Float rating);
}
