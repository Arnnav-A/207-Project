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
import org.json.JSONException;
import org.json.JSONObject;
import use_case.search.SearchDataAccessInterface;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class FileSearchDataAccessObject implements SearchDataAccessInterface {
    private final File filtersFileCSV;
    private final File listingFileJSON;
    private final PlaceFactory placeFactory;


    public FileSearchDataAccessObject(PlaceFactory placeFactory, String filtersPathCSV, String listingPathJSON) {
        this.filtersFileCSV = new File(filtersPathCSV);
        this.listingFileJSON = new File(listingPathJSON);
        this.placeFactory = placeFactory;
    }

    @Override
    public boolean isValidFilter(String filter) {
        ArrayList<String> validFilters = getSimilarFilters(filter);
        return !validFilters.isEmpty();
    }

    @Override
    public boolean isValidCity(String city) {
        return getPlaceID(city) != null;
    }

    @Override
    public ArrayList<Place> getListing(String city, String filter) {
        ArrayList<Place> listing = new ArrayList<>();
        if (!saveListing(city, filter)) {
            return listing;
        }
        try {
            JSONObject listingJSONObject = new JSONObject(new String(Files.readAllBytes(listingFileJSON.toPath())));
            JSONArray listingJSON;
             try {
                listingJSON = listingJSONObject.getJSONArray("features");
            } catch (JSONException e) {
                 return listing;
            }
            for (Object place: listingJSON) {
                try {
                    JSONObject placeProperties = ((JSONObject) place).getJSONObject("properties");
                    String name = placeProperties.getString("name");
                    String address = placeProperties.getString("formatted");
                    Double latitude = placeProperties.getDouble("lat");
                    Double longitude = placeProperties.getDouble("lon");
                    ArrayList<Double> coordinates = new ArrayList<>();
                    coordinates.add(latitude);
                    coordinates.add(longitude);
                    String tags = placeProperties.getJSONArray("categories").toString();
                    String placeCity = placeProperties.getString("city");
                    listing.add(placeFactory.create(name, address, coordinates, tags, placeCity));
                } catch (JSONException ignored) {
                }
            }
            System.out.println(listingJSONObject);
            return listing;
        } catch (IOException e) {
            return listing;
        }
    }

    private String getPlaceID(String city) {
        String API_TOKEN = System.getenv("API_TOKEN");
        String cityGeocode; // Initializing the placeID for city being searched
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        // Using Geocoding API from Geoapify.com to get the placeID for the city being searched.

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
            return cityGeocode;
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    private boolean saveListing(String city, String filter) {
        String searchLimit = "40";
        String API_TOKEN = System.getenv("API_TOKEN");
        String categories = getSimilarFilters(filter).toString().replace("[","").replace("]","");
        String cityGeocode = getPlaceID(city); // Initializing the placeID for city being searched
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        OkHttpClient client = builder.build();

        // Using Places API from Geoapify.com to get the points of interest in the city being searched.

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
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private ArrayList<String> getSimilarFilters(String filter) {
        ArrayList<String> similarFilters = new ArrayList<>();
        ArrayList<String> similarFilters2 = new ArrayList<>();
        try {
            Scanner reader = new Scanner(filtersFileCSV);
            String filter1 = filter.replace(" ", ".").toLowerCase(); // replacing " " with "." to search for filters
            String filter2 = filter.replace(" ", "_").toLowerCase(); // replacing " " with "_" to search for filters
            String filter3 = filter.split(" ")[0].toLowerCase(); // if the above doesn't find a filter, then look for filters with just the first word of search
            while (reader.hasNextLine() && similarFilters.isEmpty()) {
                String data = reader.nextLine();
                if (data.contains(filter1)) {
                    similarFilters.add(data);
                }
                if (data.contains(filter2)) {
                    similarFilters.add(data);
                }
                if (data.contains(filter3)) {
                    similarFilters2.add(data);
                }
            }
            if (similarFilters.isEmpty()) {
                return similarFilters2;
            }
            return similarFilters;
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public ArrayList<String> getAllFilters() {
        ArrayList<String> filters = new ArrayList<>();
        try {
            Scanner reader = new Scanner(filtersFileCSV);
            while (reader.hasNextLine()) {
                filters.add(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        return filters;
    }
}
