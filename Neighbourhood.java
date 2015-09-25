package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a neighbourhood having a name in a city
 */
public class Neighbourhood implements GeoArea {
    private String neighborhoodName;
    private City city;

    public Neighbourhood(String neighborhoodName, City city){
        this.neighborhoodName = neighborhoodName;
        this.city = city;
    }

    public City getCity(){
        return city;
    }
    public String getCityString(){
        return city.getCityString();
    }

    public List<String> getAllGeoStrings() {
        List<String> geoString = new ArrayList<String>();
        String acc = "";
        String[] split = neighborhoodName.split(" ");
        for (int i = 0; i < split.length; i++){
            acc = (acc + " " + split[i]).trim();
            geoString.add(acc + ", " + this.getCityString());
        }
            return geoString;
            //first -> Fancy, City, Prov, Country Code
            //second -> Fancy Slopes, City, Prov, Country Code
            //third -> Fancy Slopes Drive, City, Prov Country Code

    }
    /**
     * Two neighbourhoods are equal if they have the same city and same name
     * When o is a City, this neighbourhood is equal to it, if it has the same city.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // will have to revisit this if we add subtypes of GeoArea other than City and Neighborhood
        if (o == null || !(o instanceof GeoArea)) return false;

        // when o is a City, this Neighborhood is equal to o if the cities are equal
        if (o instanceof City)
            return city.equals(o);

        // must be a Neighborhood
        Neighbourhood other = (Neighbourhood) o;

        return (city.equals(other.city));
    }

    @Override
    public int hashCode() {
        return city != null ? city.hashCode() : 0;
    }
}
