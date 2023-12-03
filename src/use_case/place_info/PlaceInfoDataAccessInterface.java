package use_case.place_info;

import entity.Place;

public interface PlaceInfoDataAccessInterface {

    Place getPlaceFromName(String name);
}
