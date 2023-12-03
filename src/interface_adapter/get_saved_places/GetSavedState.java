package interface_adapter.get_saved_places;

import java.util.ArrayList;

public class GetSavedState {

    private ArrayList<String> places = new ArrayList<>();

    public GetSavedState() {}

    public ArrayList<String> getPlaces() {
        return this.places;
    }

    public void setPlaces(ArrayList<String> places) {
        this.places = places;
    }
}
