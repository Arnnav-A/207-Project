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

public class FilePlaceInfoDataAccessObject implements PlaceInfoDataAccessInterface {

    private final File listingFileJSON;
    private final PlaceFactory placeFactory;

    public FilePlaceInfoDataAccessObject(String listingFileJSON, PlaceFactory placeFactory) {
        this.listingFileJSON = new File(listingFileJSON);
        this.placeFactory = placeFactory;
    }

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
