package entity;

import java.util.ArrayList;

public interface Filter {

    String getName();
    CommonFilter getSubFilter(String name);
    ArrayList<String> getSubFilterNames();
    void setSubFilter(CommonFilter subFilter);

}
