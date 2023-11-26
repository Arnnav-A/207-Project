package data_access;

import entity.Place;
import entity.PlaceFactory;
import use_case.place_info.PlaceInfoDataAccessInterface;

import java.io.File;
import java.util.ArrayList;

public class FilePlaceInfoDataAccessObject implements PlaceInfoDataAccessInterface {

    private final File listingFileJSON;
    private final PlaceFactory placeFactory;

    public FilePlaceInfoDataAccessObject(String listingFileJSON, PlaceFactory placeFactory) {
        this.listingFileJSON = new File(listingFileJSON);
        this.placeFactory = placeFactory;
    }

    @Override
    public Place getPlaceFromName(String name) {
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(43.6688535);
        coordinates.add(-79.3859621);
        return placeFactory.create("McDonald's", "675 Yonge St", coordinates,
                "Fast Food", "Toronto");
    }
}
