package ca.ubc.cs.cpsc210.quiz.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a route having a list of legs and a distance.
 */
public class Route {
    private List<Leg> legs;
    private int distance;


    public Route() {
        this.legs = new ArrayList<Leg>();
        distance = 0;
    }

    public void addLeg(Leg leg){
        this.legs.add(leg);

    }

    public List<Leg> getLegs(){
        return legs;
    }

    public int getDistance(){
        int total = 0;
        for (Leg l: legs){
            total += l.getDistance();
        }
        return total;
    }
}
