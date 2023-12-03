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

### Opening the Place API website to create an account
To use the API to launch the main, the first step is to open this website: https://www.geoapify.com/places-api.
After clicking log-in on this website, there is a link to create an account on the log-in page. Users can create an
account to get the API key.
![image5.png](src\assets\image5.png)

### Adding a new project to generate the API key
After logging in the account, users need to first add a new project to generate the API key. Simply click the "Add a new
project" and add a project name.
![image6.png](src\assets\image6.png)

### Copying the API key
After creating a new project, there is an existed API key in the project. SImply copy the API key and return to the
program.
![image6.png](src\assets\image7.png)

### Setting up the API token
There is a three-dot button at the top of IntelliJ, simply click it and click the "Edit" to edit the configuration.
![image8.png](src\assets\image8.png)
After clicking the "Edit" button, change the Environment Variable to "API_TOKEN=API_KEY" and API_KEY is the API key that
users just copied from the Places API.
![image9.png](src\assets\image9.png)

### Opening the project
After opening the project, the main window will show two search boxes to enter a city and a filter, and several buttons
including "Get filter", "Search", "Saved Places", "Get history", and "Clear history".
![image1.png](src\assets\image1.png)

### Searching a city with a filter
After entering a valid city name and a filter, users can click the "Search" button to get a list of places.
![image2.png](src\assets\image2.png)
![image3.png](src\assets\image3.png)

### Getting filters
Users can access all filters available in this system by clicking the "Get filter" button. Also, they can add filter
from the broad category to a narrow category.
![image4.png](src\assets\image4.png)

### Getting places' information
After getting a list of places, users can get detailed information by clicking each place.
![image10.png](src\assets\image10.png)

### Saving places as favorite
If users want to save a place for future use, users can click the "Save as favorite".
![image11.png](src\assets\image11.png)
After saving the place, users can find it by clicking "Back" and clicking "Saved places".
![image12.png](src\assets\image12.png)

### Getting history and clearing history
After each search, the search information will be saved in the system, which can be found by clicking "Get history". If
users want to clear all history, just click "Clear history".
![image13.png](src\assets\image13.png)
![image14.png](src\assets\image14.png)

We hope you enjoy the PlaceFinder!


