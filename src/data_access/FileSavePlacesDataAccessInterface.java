package data_access;

import entity.Place;
import entity.PlaceFactory;
import use_case.get_saved_places.GetSavedDataAccessInterface;
import use_case.save_places.SavePlacesDataAccessInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSavePlacesDataAccessInterface implements SavePlacesDataAccessInterface, GetSavedDataAccessInterface {

    private final String listingFileJSON;

    private final File savedPlacesFileCSV;
    private final PlaceFactory placeFactory;


    public FileSavePlacesDataAccessInterface(String listingFileJSON, String savedPlacesFileCSV, PlaceFactory placeFactory) {
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

    public ArrayList<String> getPlaces() {
        ArrayList<String> places = new ArrayList<>();
        try {
            Scanner reader = new Scanner(savedPlacesFileCSV);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] placeLine = data.split(",");
                places.add(placeLine[0]);
            }
            return places;
        } catch (FileNotFoundException e) {
            return places;
        }
    }
}
