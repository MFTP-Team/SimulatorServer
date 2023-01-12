package com.cpe.sumulationserver.util;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.model.SensorEntity;

/**
 * Classe statique servant d'utilitaire pour la manipulation de coordonnées géographique
 */
public class CoordinateUtil {

    /**
     * Fonction permettant de calculer la distance entre deux points
     * @param lat1 latitude point 1
     * @param lon1 longitude point 1
     * @param lat2 latitude point 2
     * @param lon2 longitude point 2
     * @param unit unité de mesure 'K' pour kilomètres ou 'N'
     * @return
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /**
     * Fonction permettant de calculer la distance entre deux points de classe Sensor & Fire
     * @param sensor
     * @param fire
     * @return
     */
    public static double distance(SensorEntity sensor, FireEntity fire) {
        return distance(sensor.getLatitude(), sensor.getLongitude(), fire.getLatitude(), fire.getLongitude(), 'K');
    }
}
