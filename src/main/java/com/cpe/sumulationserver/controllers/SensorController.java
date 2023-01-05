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

    @PostMapping("/add")
    public ResponseEntity<SensorEntity> addSensor(@RequestBody SensorEntity sensorEntity) {
        return ResponseEntity.ok(sensorService.addSensor(sensorEntity));
    }

    @DeleteMapping("/delete/{id}")
    public void deletesensor(@PathVariable("id") int sensorId) {
        sensorService.deleteSensor(sensorId);
    }
    @CrossOrigin("*")
    @GetMapping("/get/{id}")
    public ResponseEntity<SensorEntity> getsensor(@PathVariable("id") int sensorId) {
        return ResponseEntity.ok(sensorService.getSensor(sensorId));
    }

    @PutMapping("/edit")
    public ResponseEntity<SensorEntity> editsensor(@RequestBody SensorEntity sensorEntity) {
        return ResponseEntity.ok(sensorService.editSensor(sensorEntity));
    }
}
