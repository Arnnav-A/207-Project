package data_access;

import entity.Place;
import entity.PlaceFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.place_info.PlaceInfoDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class to get all place attributes for a given place name in the current search
 */
public class FilePlaceInfoDataAccessObject implements PlaceInfoDataAccessInterface {

    private final File listingFileJSON;
    private final PlaceFactory placeFactory;

    /**
     * Constructor for a File Place Info Data Access Object
     * @param listingFileJSON The JSON file where we stored the search results
     * @param placeFactory The place factory to create place entities
     */
    public FilePlaceInfoDataAccessObject(String listingFileJSON, PlaceFactory placeFactory) {
        this.listingFileJSON = new File(listingFileJSON);
        this.placeFactory = placeFactory;
    }

    /**
     * Method gives the place entity from the name of the place
     * @param name The name of the place being searched for
     * @return A place entity with the given name
     */
    @Override
    public Place getPlaceFromName(String name) {
        Place errorPlace = placeFactory.create("Place Details Not Found", "", new ArrayList<>(), "", "");
        try {
            JSONObject listingJSONObject = new JSONObject(new String(Files.readAllBytes(listingFileJSON.toPath())));
            JSONArray listingJSON;
            try {
                listingJSON = listingJSONObject.getJSONArray("features");
            } catch (JSONException e) {
                return errorPlace;
            }
            for (Object place: listingJSON) {
                try {
                    JSONObject placeProperties = ((JSONObject) place).getJSONObject("properties");
                    String placeName = placeProperties.getString("address_line1");
                    if (Objects.equals(placeName, name)) {
                        String address = placeProperties.getString("address_line2");
                        Double latitude = placeProperties.getDouble("lat");
                        Double longitude = placeProperties.getDouble("lon");
                        ArrayList<Double> coordinates = new ArrayList<>();
                        coordinates.add(latitude);
                        coordinates.add(longitude);
                        String tags = placeProperties.getJSONArray("categories").toString();
                        String placeCity = placeProperties.getString("city");
                        return placeFactory.create(placeName, address, coordinates, tags, placeCity);
                    }
                } catch (JSONException ignored) {}
            }
            return errorPlace;
        } catch (IOException e) {
            return errorPlace;
        }
    }
}
