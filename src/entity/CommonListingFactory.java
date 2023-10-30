package entity;

import java.util.ArrayList;

public class CommonListingFactory implements ListingFactory{

    @Override
    public Listing create(ArrayList<Place> points, String city, String filter) {
        return new CommonListing(points, city, filter);
    }
}
