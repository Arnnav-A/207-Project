package data_access;

import entity.Place;
import entity.PlaceFactory;
import use_case.save_places.SavePlacesDataAccessObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileSavePlacesDataAccessObject implements SavePlacesDataAccessObject {

    private final String listingFileJSON;

    private final File savedPlacesFileCSV;
    private final PlaceFactory placeFactory;


    public FileSavePlacesDataAccessObject(String listingFileJSON, String savedPlacesFileCSV, PlaceFactory placeFactory) {
        this.listingFileJSON = listingFileJSON;
        this.savedPlacesFileCSV = new File(savedPlacesFileCSV);
        this.placeFactory = placeFactory;
    }

    @Override
    public void save(String name) {
        FilePlaceInfoDataAccessObject filePlaceInfoDataAccessObject = new FilePlaceInfoDataAccessObject(listingFileJSON, placeFactory);
        Place place = filePlaceInfoDataAccessObject.getPlaceFromName(name);
        save(place);
    }

    private void save(Place place) {
        try {
            FileWriter filewriter = new FileWriter(savedPlacesFileCSV, true);
            String data = place.getName() + ',' +
                    place.getAddress() + ',' +
                    place.getCoordinates() + ',' +
                    place.getTags() + ',' +
                    place.getCity() + '\n';
            filewriter.write(data);
            filewriter.flush();
            filewriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
