package entity;


import java.util.ArrayList;

public interface ListingFactory {

    Listing create(ArrayList<Place> points, String city, String filter);
}
