# Travel Recommendations (Place Finder)

## The objective
Our team's project is about searching and managing places of interest to visit based on a destination city. In this 
system, users can search places in a city with filters that they want to find. After getting a list of places, the user
can click on each place to get further information(e.g., google map link). Users can also save the city as Favorites, so
they can refer to after closing the results page. The system can also save each search's information with the city,
filter, and the searching time. In case users want to see know what filters can be selected, they can get filters that
are available in this system in the main window.

## Program Specification 

The program is going to include multiple features. 
1. __Searching__: Users can search places in a city with filters. If the city is not in the system, users will receive a
"city not found" message. If the filter is not in the system, users will receive a "filter not found" message. If the
city and filter are valid, users will get a list of places.
2. __Getting filer__: Get Filter use case allows users to look up and choose a specific filter for their place want to
search. There are more than 500 filters overall, and up to 4 levels of choice for more precise filtering. Once the user
has chosen their filter, by clicking "Apply this filter", the selection is simply inputted to search space and no need
for user to do extra operations.
3. __Finding places information__: After getting the list of places, user can click on each place to get further
information.
4. __Managing saved places__: After clicking on each place, if users are interested in this place they can click on the
"Save as favorite" to save it for late use.
5. __Managing searching history__: Each time when users search something, the system will save the information of the
   search. Users can click "get history" to get the search history. Conversely, users can clear all the searching history
   by clicking "clear history".

## Feature Implemented
* Searching and filtering (By Arnnav, Nicolas, and Jia)
* Getting filters (By Andrew)
* Finding places information (By Nicolas)
* Managing saved places (By Arnnav)
* Saving searching history (By Jia and Arnnav)
* Clearing searching history (By Jia)

## Geocoding API
Geocoding API can translate the names of cities and filters to Geoapify-friendly IDs. Therefore, Our program can use the
ID of cities and filters to search with Places API.

https://www.geoapify.com/geocoding-api

## Places API
Places API can search places in a city and narrowing down the ranges of places with a filter using the ID from Geocoding
API.

https://www.geoapify.com/places-api

## How to use the program

## Opening the project
After opening the project, the main window will show two search boxes to enter a city and a filter, and several buttons
including "Get filter", "Search", "Saved Places", "Get history", and "Clear history".
![img_1.png]

