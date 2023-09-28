# Travel Recommendations

## Problem Domain

Out team's project is about travel recommendations/places to visit based on a destination city. 

## Project Overview 

We want to implement a travel-planning system. The application is going to include multiple features. Firstly, users can provide a city. Then, based on the location provided, with the assistance of the OpenTripMap API, the application gives a recommendation of points of interest nearby. Moreover, the user can also use the tags provided by the API to add filters based on their preferences. We are also considering adding features such as: 

* Trip scheduling;
* Weather (using weather API); 
* Flight/Hotel recommendations; 
* Private Review/Feedback. 

## OpenTripMap API

https://opentripmap.io/docs#/

The OpenTripMap API is an interface to access a database of points of interest. It also allows users to get the geographical location (latitude and longitude) of a city, which can be used to give recommendations in a certain radius. It also provides tags that allow users to filter the locations provided. 

![Screenshot 1](https://cdn.discordapp.com/attachments/1156382532816867449/1156382638710476820/Input_screenshot.png?ex=65156d81&is=65141c01&hm=d2b15638a9442254d30ca238c52d58609e05a88939b242997dfe4842f54224d1&)

![Screenshot 2](https://media.discordapp.net/attachments/1156382532816867449/1156382639041806416/Output_screenshot.png?ex=65156d81&is=65141c01&hm=d2c61e80355d378f597263d7610ab1cbe6bd894674a0b02a3a3ca98df127dbd0&=&width=1106&height=485)  

## Example Java Code Output

```json
{
  "country":"CA",
  "timezone":"America/Toronto",
  "name":"toronto",
  "lon":-79.4163,
  "lat":43.70011,
  "population":2600000,
  "status":"OK"
}
```
This is the result of the first method 'geoname' in the OpenTripMap API, when used with keyword: "toronto".

## Technical Problems

* We are currently facing issues in managing maven builds to add the module dependencies for this project.
