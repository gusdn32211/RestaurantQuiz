package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a name having a name, province/state code and country code
 */
public class City implements GeoArea{
    String cityName;
    String province;
    String countryCode;

    public City(String cityName,String province,String countryCode) throws IllegalArgumentException{
        this.cityName = cityName;
        this.province = province;
        this.countryCode = countryCode;

        if(!(province.length() == 2) || !(countryCode.length() == 2))
            throw new IllegalArgumentException();
        }


    public String getCityString() throws  IllegalArgumentException{
        return cityName + ", " + province + ", " + countryCode;
    }
    //"city, province, country" (e.g. "Vancouver, BC, CA")

    public List<String> getAllGeoStrings(){
        List<String> geoString = new ArrayList<String>();
        geoString.add(this.getCityString());
        return geoString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof GeoArea)) return false;

        if (o instanceof Neighbourhood){
            return o.equals(this);}

        City city = (City) o;

        if (cityName != null ? !cityName.equals(city.cityName) : city.cityName != null) return false;
        if (countryCode != null ? !countryCode.equals(city.countryCode) : city.countryCode != null) return false;
        if (province != null ? !province.equals(city.province) : city.province != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cityName != null ? cityName.hashCode() : 0;
        result = 31 * result + (province != null ? province.hashCode() : 0);
        result = 31 * result + (countryCode != null ? countryCode.hashCode() : 0);
        return result;
    }
}
