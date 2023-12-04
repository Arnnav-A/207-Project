package entity;

import java.util.List;

public class CommonListing implements Listing {

    private final List<Place> points;
    private final String city;
    private final String filter;

    CommonListing(List<Place> points, String city, String filter) {
        this.points = points;
        this.city = city;
        this.filter = filter;
    }

    @Override
    public List<Place> getPoints() {
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

}
