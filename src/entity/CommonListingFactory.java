package entity;

import java.util.List;

public class CommonListingFactory implements ListingFactory{

    @Override
    public Listing create(List<Place> points, String city, String filter) {
        return new CommonListing(points, city, filter);
    }
}
