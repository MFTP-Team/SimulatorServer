package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.SensorEntity;
import com.cpe.sumulationserver.services.SensorService;

import mil.nga.sf.geojson.FeatureCollection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @CrossOrigin("*")
    @GetMapping("/geo/polygon")
    private ResponseEntity<FeatureCollection> getStationGeoPolygon() {
        return ResponseEntity.ok(sensorService.getAllSensorsGeoPolygon());
    }

    @CrossOrigin("*")
    @GetMapping("/geo/point")
    private ResponseEntity<FeatureCollection> getStationGeoPoint() {
        return ResponseEntity.ok(sensorService.getAllSensorsGeoPoint());
    }

    @CrossOrigin("*")
    @PostMapping("/add")
    public ResponseEntity<SensorEntity> addSensor(@RequestBody SensorEntity sensorEntity) {
        return ResponseEntity.ok(sensorService.addSensor(sensorEntity));
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SensorEntity> deleteSensor(@PathVariable("id") int sensorId) {
        return ResponseEntity.ok(sensorService.deleteSensor(sensorId));
    }
    @CrossOrigin("*")
    @GetMapping("/get/{id}")
    public ResponseEntity<SensorEntity> getSensor(@PathVariable("id") int sensorId) {
        return ResponseEntity.ok(sensorService.getSensor(sensorId));
    }

    @CrossOrigin("*")
    @PutMapping("/edit")
    public ResponseEntity<SensorEntity> editSensor(@RequestBody SensorEntity sensorEntity) {
        return ResponseEntity.ok(sensorService.editSensor(sensorEntity));
    }
}
