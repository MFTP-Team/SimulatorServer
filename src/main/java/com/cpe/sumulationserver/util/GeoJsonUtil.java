package com.cpe.sumulationserver.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.model.SensorEntity;
import com.cpe.sumulationserver.model.TruckEntity;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.Point;
import mil.nga.sf.geojson.Polygon;
import mil.nga.sf.geojson.Position;

public class GeoJsonUtil {

    public static Feature getPolygonFromSensor(SensorEntity sensor){
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<String, Object>();
        Polygon polygon = new Polygon();
        List<Position> positions = new ArrayList<Position>();
        Integer NUM_POINTS = 30;
        for (int i = 0; i < NUM_POINTS; i++) {
            double angle = 360.0 / NUM_POINTS * i;
            double latitude = sensor.getLatitude() + 0.00001*sensor.getRadius() * Math.cos(Math.toRadians(angle));
            double longitude = sensor.getLongitude() + 0.00001*sensor.getRadius() * Math.sin(Math.toRadians(angle));
            positions.add(new Position(latitude, longitude));
        }
        List<List<Position>> cord = new ArrayList<>();
        cord.add(positions);
        polygon.setCoordinates(cord);
        properties.put("id", sensor.getId());
        properties.put("type", "SENSOR");
        feature.setGeometry(polygon);
        feature.setProperties(properties);
        return feature;
    }

    public static Feature getPointFromSensor(SensorEntity sensor){
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<String, Object>();
        Point point = new Point();
        point.setCoordinates(new Position(sensor.getLatitude(),sensor.getLongitude()));
        properties.put("id", sensor.getId());
        properties.put("type", "SENSOR");
        feature.setGeometry(point);
        feature.setProperties(properties);
        return feature;
    }

    public static Feature getPolygonFromFire(FireEntity fire){
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<String, Object>();
        Polygon polygon = new Polygon();
        List<Position> positions = new ArrayList<Position>();
        Integer NUM_POINTS = 30;
        for (int i = 0; i < NUM_POINTS; i++) {
            double angle = 360.0 / NUM_POINTS * i;
            double latitude = fire.getLatitude() + 0.00001*fire.getIntensity()* Math.cos(Math.toRadians(angle));
            double longitude = fire.getLongitude() + 0.00001*fire.getIntensity() * Math.sin(Math.toRadians(angle));
            positions.add(new Position(latitude, longitude));
        }
        List<List<Position>> cord = new ArrayList<>();
        cord.add(positions);
        polygon.setCoordinates(cord);
        properties.put("id", fire.getId());
        properties.put("type", "FIRE");
        feature.setGeometry(polygon);
        feature.setProperties(properties);
        return feature;
    }

    public static Feature getPointFromFire(FireEntity fire){
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<String, Object>();
        Point point = new Point();
        point.setCoordinates(new Position(fire.getLatitude(),fire.getLongitude()));
        properties.put("id", fire.getId());
        properties.put("type", "FIRE");
        feature.setGeometry(point);
        feature.setProperties(properties);
        return feature;
    }

    public static Feature getPointFromTruck(TruckEntity truck){
        Feature feature = new Feature();
        Map<String, Object> properties = new HashMap<String, Object>();
        Point point = new Point();
        point.setCoordinates(new Position(truck.getLatitude(),truck.getLongitude()));
        properties.put("id", truck.getId());
        properties.put("type", "TRUCK");
        feature.setGeometry(point);
        feature.setProperties(properties);
        return feature;
    }



    
    
}
