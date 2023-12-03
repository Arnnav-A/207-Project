package use_case.get_saved_places;

import java.util.ArrayList;

public class GetSavedOutputData {

    private ArrayList<String> places;

    public GetSavedOutputData(ArrayList<String> places) {
        this.places = places;
    }

    public ArrayList<String> getPlaces() {
        return this.places;
    }
}
