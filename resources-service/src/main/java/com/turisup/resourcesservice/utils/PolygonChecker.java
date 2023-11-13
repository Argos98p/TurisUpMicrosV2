package com.turisup.resourcesservice.utils;

import com.turisup.resourcesservice.Model.Coordinate;

import java.util.List;

public class PolygonChecker {
    private static final double EPSILON = 1e-5; // Puedes ajustar el valor según tus necesidades de precisión

    public static boolean isPolygonClosed(List<Coordinate> coordinates) {
        if (coordinates == null || coordinates.size() < 3) {
            // Un polígono debe tener al menos tres puntos
            return false;
        }

        Coordinate firstPoint = coordinates.get(0);
        Coordinate lastPoint = coordinates.get(coordinates.size() - 1);

        return areCoordinatesEqual(firstPoint, lastPoint);
    }

    private static boolean areCoordinatesEqual(Coordinate c1, Coordinate c2) {
        // Compara las latitudes y longitudes con un margen de error (EPSILON)
        return Math.abs(c1.getLatitude() - c2.getLatitude()) < EPSILON
                && Math.abs(c1.getLongitude() - c2.getLongitude()) < EPSILON;
    }



}
