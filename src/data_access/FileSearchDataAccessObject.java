package data_access;

import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;
import entity.Place;
import entity.PlaceFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.search.SearchDataAccessInterface;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FileSearchDataAccessObject implements SearchDataAccessInterface {
    private final File filtersFileCSV;
    private final File listingFileJSON;
    private final PlaceFactory placeFactory;


    public FileSearchDataAccessObject(PlaceFactory commonPlaceFactory, String filtersPathCSV, String listingPathJSON) {
        this.filtersFileCSV = new File(filtersPathCSV);
        this.listingFileJSON = new File(listingPathJSON);
        this.placeFactory = commonPlaceFactory;
    }

    @Override
    public boolean isValidFilter(String filter) {
        ArrayList<String> validFilters = getSimilarFilters(filter);
        return !validFilters.isEmpty();
    }

    @Override
    public ArrayList<Place> getListing(String city, String filter) {
        ArrayList<Place> listing = new ArrayList<>();
        if (!saveListing(city, filter)) {
            return listing;
        }
        try {
            JSONObject listingJSONObject = new JSONObject(new String(Files.readAllBytes(listingFileJSON.toPath())));
            JSONArray listingJSON = listingJSONObject.getJSONArray("features");
            for (Object place: listingJSON) {
                JSONObject placeProperties = ((JSONObject) place).getJSONObject("properties");
                listing.add(placeFactory.create(placeProperties.getString("name"), "", new ArrayList<>(), placeProperties.getJSONArray("categories").toString(), 0f));
            }
            System.out.println(listingJSONObject);
            return listing;
        } catch (IOException e) {
            return listing;
        }
    }

    private boolean saveListing(String city, String filter) {
        String searchLimit = "20";
        String API_TOKEN = System.getenv("API_TOKEN");
        String categories = getSimilarFilters(filter).toString().replace("[","").replace("]","");
        String cityGeocode; // Initializing the placeID for city being searched
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();
        /**
         * Using Geocoding API from Geoapify.com to get the placeID for the city being searched.
         */
        Request requestCityGeocode = new Request.Builder()
                .url(String.format("https://api.geoapify.com/v1/geocode/search?text=%s&type=city&format=json&apiKey=%s", city.replace(" ", "%20"), API_TOKEN))
                .build();
        Response responseCityGeocode;
        try {
            responseCityGeocode = client.newCall(requestCityGeocode).execute();
            assert responseCityGeocode.body() != null;
            JSONObject responseCityGeocodeBody = new JSONObject(responseCityGeocode.body().string());
            JSONArray allCityDetails = (JSONArray) responseCityGeocodeBody.get("results");
            JSONObject cityDetails = (JSONObject) allCityDetails.get(0);
            cityGeocode = (String) cityDetails.get("place_id");
        } catch (IOException e) {
            return false;
        }
        /**
         * Using Places API from Geoapify.com to get the points of interest in the city being searched.
         */
        Request requestPlaces = new Request.Builder()
                .url(String.format("https://api.geoapify.com/v2/places?categories=%s&filter=place:%s&limit=%s&apiKey=%s", categories, cityGeocode, searchLimit, API_TOKEN))
                .build();
        Response responsePlaces;
        try {
            responsePlaces = client.newCall(requestPlaces).execute();
            assert responsePlaces.body() != null;
            JSONObject responseBody = new JSONObject(responsePlaces.body().string());
            FileWriter fileWriter = new FileWriter(listingFileJSON);
            fileWriter.write(responseBody.toString());
            fileWriter.flush();
            fileWriter.close();
            JSONArray locations = (JSONArray) responseBody.get("features");
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private ArrayList<String> getSimilarFilters(String filter) {
        ArrayList<String> similarFilters = new ArrayList<>();
        try {
            Scanner reader = new Scanner(filtersFileCSV);
            while (reader.hasNextLine() && similarFilters.isEmpty()) {
                String data = reader.nextLine();
                if (data.contains(filter.toLowerCase())) {
                    similarFilters.add(data);
                }
            }
            return similarFilters;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

}
