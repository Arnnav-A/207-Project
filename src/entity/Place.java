package entity;

import java.util.List;

public interface Place {

    String getName();

    String getAddress();

    List<Double> getCoordinates();

    String getTags();

    String getCity();
}
