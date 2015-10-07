package ca.ubc.cs.cpsc210.quiz.activity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pcarter on 14-11-06.
 *
 * Manager for markers plotted on map
 */
public class MarkerManager {
    private GoogleMap map;
    private List<Marker> markerList;


    /**
     * Constructor initializes manager with map for which markers are to be managed.
     * @param map  the map for which markers are to be managed
     */
    public MarkerManager(GoogleMap map) {
        this.map = map;
        markerList = new ArrayList<Marker>();
    }

    /**
     * Get last marker added to map
     * @return  last marker added
     */
    public Marker getLastMarker() {
        return markerList.get(markerList.size()-1);
    }


    /**
     * Add green marker to show position of restaurant
     * @param point   the point at which to add the marker
     * @param title   the marker's title
     */
    public void addRestaurantMarker(LatLng point, String title) {
        MarkerOptions markerOptions = new MarkerOptions();
        map.addMarker(markerOptions
                .position(point)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .title(title));
    }

    /**
     * Add a marker to mark latest guess from user.  Only the most recent two positions selected
     * by the user are marked.  The distance from the restaurant is used to create the marker's title
     * of the form "xxxx m away" where xxxx is the distance from the restaurant in metres (truncated to
     * an integer).
     *
     * The colour of the marker is based on the distance from the restaurant:
     * - red, if the distance is 3km or more
     * - somewhere between red (at 3km) and green (at 0m) (on a linear scale) for other distances
     *
     * @param latLng
     * @param distance
     */
    public void addMarker(LatLng latLng, double distance) {
        MarkerOptions markerOptions = new MarkerOptions();
        int round = (int) Math.ceil(distance);
        String distanceAway = Integer.toString(round);
        Marker marker = map.addMarker(markerOptions
                .position(latLng)
                .title(distanceAway + "m away")
                .icon(BitmapDescriptorFactory.defaultMarker(getColour(distance))));

        markerList.add(marker);

        if (markerList.size() > 2){
            Marker m = markerList.get(0);
            m.remove();
            markerList.remove(0);
        }
    }

    /**
     * Remove markers that mark user guesses from the map
     */
    public void removeMarkers() {
        for (int i = 0; i < markerList.size(); i++) {
            markerList.remove(i);
        }
        markerList.clear();
    }

    /**
     * Produce a colour on a linear scale between red and green based on distance:
     *
     * - red, if distance is 3km or more
     * - somewhere between red (at 3km) and green (at 0m) (on a linear scale) for other distances
     * @param distance  distance from restaurant
     * @return  colour of marker
     */
    private float getColour(double distance) {
        float f = (float) distance;
        if (distance >= 3000) {
            return 0.0f;
        } else return (120 - ((f/3000)*120));
    }
}

