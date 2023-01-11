package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.model.SensorEntity;
import com.cpe.sumulationserver.repository.SensorRepository;
import com.cpe.sumulationserver.util.Constantes;
import com.cpe.sumulationserver.util.CoordinateUtil;
import com.cpe.sumulationserver.util.GeoJsonUtil;

import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.geometry.jts.FactoryFinder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.h2.util.geometry.GeoJsonUtils;
import org.h2.util.json.JSONString;
import org.json.JSONObject;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;
import mil.nga.sf.geojson.Polygon;
import mil.nga.sf.geojson.Position;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Value("${simulator.python-api.url}")
    private String PythonServerURL;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public SensorEntity addSensor(SensorEntity sensorEntity) {
        return this.sensorRepository.save(sensorEntity);
    }

    public SensorEntity deleteSensor(int sensorId) {
        return this.sensorRepository.deleteById(sensorId);
    }

    public SensorEntity getSensor(int sensorId) {
        return this.sensorRepository.findById(sensorId);
    }

    public SensorEntity editSensor(SensorEntity sensorEntity) {
        return this.sensorRepository.save(sensorEntity);
    }

    public FeatureCollection getAllSensorsGeoPoint() {
        List<SensorEntity> list = this.sensorRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (SensorEntity sensor : list) {
            Feature pointFeature = GeoJsonUtil.getPointFromSensor(sensor);
            featureCollection.addFeature(pointFeature);
        }
        return featureCollection;
    }

    public FeatureCollection getAllSensorsGeoPolygon() {
        List<SensorEntity> list = this.sensorRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (SensorEntity sensor : list) {
            Feature polygonFeature = GeoJsonUtil.getPolygonFromSensor(sensor);
            featureCollection.addFeature(polygonFeature);
        }
        return featureCollection;
    }

    public void triggerFire(FireEntity fire) {
        log.info("Triggering fire");
        this.sensorRepository.findAll().forEach(sensor -> {
            // Le sensor est-il dans le rayon du feu ?
            double distance = CoordinateUtil.distance(sensor, fire);
            log.info("distance: {}", distance);
            Double sensorDistance = sensor.getRadius() * Constantes.RADIUS_COEFFICIENT;
            if (distance < sensorDistance) {
                // les deux rayons se touchent
                log.info("Sensor {} is in the fire radius", sensor.getId());
                // Le sensor est dans le rayon du feu
                // On lance une alerte à l'API Python
                try {
                    // On ajoute les coord du sensor
                    JSONObject jsonData = new JSONObject();
                    jsonData.put("latitude", sensor.getLatitude());
                    jsonData.put("longitude", sensor.getLongitude());
                    CloseableHttpClient httpClient = HttpClients.createDefault();
                    HttpPost httpPost = new HttpPost(PythonServerURL);
                    StringEntity entity = new StringEntity(jsonData.toString());
                    httpPost.setEntity(entity);
                    httpPost.setHeader("Accept", "application/json");
                    httpPost.setHeader("Content-type", "application/json");

                    CloseableHttpResponse response = httpClient.execute(httpPost);

                    //Execute and get the response.
                    log.info("Attempting to send alert to Python API");
                    HttpEntity entityReceived = response.getEntity();

                    if (entityReceived != null) {
                        try (InputStream instream = entity.getContent()) {
                            String text = new String(instream.readAllBytes(), StandardCharsets.UTF_8);
                            log.info("Response from Python API: " + text);
                        }
                    }
                this.sensorRepository.save(sensor);
                } catch (ProtocolException e) {
                        throw new RuntimeException(e);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
