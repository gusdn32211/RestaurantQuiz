package ca.ubc.cs.cpsc210.quiz.google;

import ca.ubc.cs.cpsc210.quiz.model.Leg;
import ca.ubc.cs.cpsc210.quiz.model.Route;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Parser for response from Google Directions API.
 */
public class GoogleDirectionsParser {


    public static Route parseRoute(String jsonresponse) throws JSONException {
        List<Leg> legList = new ArrayList<Leg>();

        JSONObject json = new JSONObject(jsonresponse);

        JSONObject routes = json.getJSONArray("routes").getJSONObject(0);

        for (int i = 0; i < routes.getJSONArray("legs").length(); i++) {
            JSONObject legs = routes.getJSONArray("legs").getJSONObject(i);

            Leg leg = new Leg();

            for (int j = 0; j < legs.getJSONArray("steps").length(); j++) {
                JSONObject steps = legs.getJSONArray("steps").getJSONObject(j);
                leg.addAllPoints(PolylineDecoder.decodePoly(steps.getJSONObject("polyline").getString("points")));
            }
            leg.setDistance(legs.getJSONObject("distance").getInt("value"));
            legList.add(leg);
        }
        Route route = new Route();
        for (Leg l : legList)
            route.addLeg(l);
        return route;
    }
}







