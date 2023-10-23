package entity;

import java.util.List;

public interface Place {

    String getName();

    String getDescription();

    List<Double> getCoordinates();

    String getTags();

    Float getRating();
}
