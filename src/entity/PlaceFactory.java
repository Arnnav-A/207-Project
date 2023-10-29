package entity;

import java.util.List;

public interface PlaceFactory {
    Place create(String name, String address, List<Double> coordinates, String tags, String city);
}
