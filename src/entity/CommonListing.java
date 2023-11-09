package entity;

import java.util.ArrayList;

public class CommonListing implements Listing {

    private final ArrayList<Place> points;
    private final String city;
    private final String filter;

    CommonListing(ArrayList<Place> points, String city, String filter) {
        this.points = points;
        this.city = city;
        this.filter = filter;
    }

    @Override
    public ArrayList<Place> getPoints() {
        return points;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getFilter() {
        return filter;
    }

    public Place getPlaceFromName(String name) {
        for (Place point : points) {
            if (point.getName().equals(name)) {
                return point;
            }
        }
        return null;
    }
}
