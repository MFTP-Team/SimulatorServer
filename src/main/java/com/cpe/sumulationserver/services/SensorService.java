package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.model.SensorEntity;
import com.cpe.sumulationserver.repository.SensorRepository;
import com.cpe.sumulationserver.util.CoordinateUtil;

import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.geotools.geometry.jts.FactoryFinder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.h2.util.json.JSONString;
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

    public void deleteSensor(int sensorId) {
        this.sensorRepository.deleteById(sensorId);
    }

    public SensorEntity getSensor(int sensorId) {
        return this.sensorRepository.findById(sensorId);
    }

    public SensorEntity editSensor(SensorEntity sensorEntity) {
        return this.sensorRepository.save(sensorEntity);
    }

    public FeatureCollection getAllSensorsGeo() {
        List<SensorEntity> list = this.sensorRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (SensorEntity sensor : list) {
            Feature feature = new Feature();
            Map<String, Object> properties = new HashMap<String, Object>();
            Polygon geometry = new Polygon();

            List<Position> positions = new ArrayList<Position>();
            Integer NUM_POINTS = 10;
            for (int i = 0; i < NUM_POINTS; i++) {
                double angle = 360.0 / NUM_POINTS * i;
                double latitude = sensor.getLatitude() + sensor.getRadius() * Math.cos(Math.toRadians(angle));
                double longitude = sensor.getLongitude() + sensor.getRadius() * Math.sin(Math.toRadians(angle));
                positions.add(new Position(latitude, longitude));
            }
            List<List<Position>> cord = new ArrayList<>();
            cord.add(positions);
            geometry.setCoordinates(cord);
            properties.put("id", sensor.getId());
            properties.put("type", "SENSOR");
            feature.setGeometry(geometry);
            feature.setProperties(properties);
            featureCollection.addFeature(feature);
        }
        return featureCollection;
    }

    public void triggerFire(FireEntity fire) {
        log.info("Triggering fire");
        this.sensorRepository.findAll().forEach(sensor -> {
            // Le sensor est-il dans le rayon du feu ?
            GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
            Point firePoint = geometryFactory.createPoint(new Coordinate(fire.getLatitude(), fire.getLongitude()));
            Point sensorPoint = geometryFactory.createPoint(new Coordinate(sensor.getLatitude(), sensor.getLongitude()));
            double distance = firePoint.distance(sensorPoint);
            log.info("distance: {}", distance, "firepoint : {}", firePoint.getCoordinate().toString(), "sensorPoint : {}", sensorPoint.getCoordinate().toString());
            if (distance < sensor.getRadius()) {
                // les deux rayons se touchent
                log.info("Sensor {} is in the fire radius", sensor.getId());
                // Le sensor est dans le rayon du feu
                // On lance une alerte à l'API Python
                try {
                    /*
                    URL url = new URL (this.PythonServerURL);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json");
                    con.setRequestProperty("Accept", "application/json");
                    con.setDoOutput(true);
                    StringBuilder content = new StringBuilder();
                    content.append("{");
                    content.append("\"id\": \"" + sensor.getId() + "\",");
                    content.append("\"latitude\": \"" + sensor.getLatitude() + "\",");
                    content.append("\"longitude\": \"" + sensor.getLongitude() + "\",");
                    content.append("\"intensity\": \"" + distance%sensor.getRadius() + "\",");
                    try(OutputStream os = con.getOutputStream()) {
                        byte[] input = content.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }
                    try(BufferedReader br = new BufferedReader(
                            new InputStreamReader(con.getInputStream(), "utf-8"))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine = null;
                        while ((responseLine = br.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        log.info(response.toString());
                    }*/
                    CloseableHttpClient httpclient = HttpClients.createDefault();
                    HttpPost httppost = new HttpPost(this.PythonServerURL);

                    // On ajoute les coord du sensor
                    StringBuilder data = new StringBuilder();
                    data.append("{");
                    data.append("\"id\": \"" + sensor.getId() + "\",");
                    data.append("\"latitude\": \"" + sensor.getLatitude() + "\",");
                    data.append("\"longitude\": \"" + sensor.getLongitude() + "\",");
                    data.append("\"intensity\": \"" + distance%sensor.getRadius() + "\"");
                    data.append("}");
                    StringEntity stringEntity = new StringEntity(data.toString(), StandardCharsets.UTF_8);
                    stringEntity.setContentType("application/json");
                    httppost.setEntity(stringEntity);

                    //Execute and get the response.
                    log.info("Attempting to send alert to Python API");
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    if (entity != null) {
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
