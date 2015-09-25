package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a leg that has an arbitrary number of points and a distance.  Part of a route.
 */
public class Leg {
    private List<LatLng> latLngs;
    private int distance;


    public Leg(){
         this.latLngs = new ArrayList<LatLng>();
         distance = 0;
    }

    public void addPoint(LatLng pt) {
        this.latLngs.add(pt);



    }
    public void addAllPoints(java.util.List<LatLng> pts){
        this.latLngs.addAll(pts);
    }

    public List<LatLng> getPoints(){
        return latLngs;

    }

    public void setDistance(int distance){
        this.distance = distance;

    }
    public int getDistance(){
    return distance;
    }


}
