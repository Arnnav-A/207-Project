package entity;

import java.util.List;

public class CommonPlaceFactory implements PlaceFactory {
    @Override
    public Place create(String name, String address, List<Double> coordinates, String tags, String city) {
        return new CommonPlace(name, address, coordinates, tags, city);
    }
}
