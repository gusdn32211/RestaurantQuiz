package ca.ubc.cs.cpsc210.quiz.yelp;

import ca.ubc.cs.cpsc210.quiz.model.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser for JSON data returned by Yelp.
 */
public class YelpDataParser {

    // EFFECT: Parses a JSONObject received in response to a Yelp search for restaurants, filtered by city name and
    // produces corresponding list of restaurants that are not closed. If the yelpCityFilter is null, "Vancouver" is
    // used as the city filter.
    public static List<Restaurant> parseRestaurantData(String response, String yelpCityFilter) throws JSONException {

        List<Restaurant> restaurants = new ArrayList<Restaurant>();

        JSONObject json = new JSONObject(response);

        JSONArray businesses = json.getJSONArray("businesses");
        for (int i = 0; i < businesses.length(); i++) {
            JSONObject data = businesses.getJSONObject(i);
            JSONObject location = data.getJSONObject("location");

            JSONArray category = data.getJSONArray("categories");
            String checkCity = location.getString("city");

            if (!data.getBoolean("is_closed")) {

                List<Category> categories = new ArrayList<Category>();
                if (yelpCityFilter.equals(null)) {
                    yelpCityFilter = "Vancouver";
                }
                if (location.getString("city").equals(yelpCityFilter)) {
                    for (int j = 0; j < category.length(); j++) {
                        JSONArray insideCategories = category.getJSONArray(j);
                        String catName = insideCategories.getString(0);
                        String catAlias = insideCategories.getString(1);
                        Category c = new Category(catName, catAlias);

                        categories.add(c);
                    }


                    String name = data.getString("name");
                    String id = data.getString("id");
                    String address = location.getJSONArray("address").getString(0);
                    String postalCode;
                    try {
                        postalCode = location.getString("postal_code");
                    } catch (JSONException e) {
                        postalCode = null;
                    }

                    String province = location.getString("state_code");
                    String countryCode = location.getString("country_code");
                    City city = new City(checkCity, province, countryCode);


                    GeoArea geoArea;
                    try {
                        String nbhdname = location.getJSONArray("neighborhoods").getString(0);
                        geoArea = new Neighbourhood(nbhdname, city);
                    } catch (JSONException e) {
                        geoArea = city;
                    }
                    Restaurant r = new Restaurant(name, categories, id, address, postalCode, geoArea);
                    restaurants.add(r);
                }
            }
         }
        return restaurants;
    }
}


















