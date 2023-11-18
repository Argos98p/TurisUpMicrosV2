package com.turisup.resourcesservice.utils;

import com.turisup.resourcesservice.Model.Coordinate;

import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
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

    public static boolean pointInPolygon(Coordinate punto, List<Coordinate> verticesRegion) {
        Path2D.Double path = new Path2D.Double();

        for (int i = 0; i < verticesRegion.size(); i++) {
            double x = verticesRegion.get(i).getLongitude();
            double y = verticesRegion.get(i).getLatitude();

            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }

        }

        path.closePath();

        Point2D.Double punto2D = new Point2D.Double(punto.getLongitude(), punto.getLatitude());


        return new Area(path).contains(punto2D);
    }



}
