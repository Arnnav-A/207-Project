package entity;

import java.util.ArrayList;

/**
 * The interface for all filters stored
 */
public interface Filter {

    /**
     * Method to get name of a filter
     * @return this filter's name
     */
    String getName();


    /**
     * Method to set a sub filter
     * @param subFilter: a filter that you want to set as subfilter
     */
    void setSubFilter(Filter subFilter);


    /**
     * Method to get sub filter
     * @param name: the name of subfilter that you want to choose
     * @return the subfilter you chose
     */
    Filter getSubFilter(String name);


    /**
     * Method to get array list of all sub filters' names
     * @return get all subfilters' name
     */
    ArrayList<String> getSubFilterNames();

}
