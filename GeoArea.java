package ca.ubc.cs.cpsc210.quiz.model;

import java.util.List;

/**
 * Represents a geographical area.
 */
public interface GeoArea {

    String getCityString();

    List<String> getAllGeoStrings();
}
