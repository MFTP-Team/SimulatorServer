package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.TruckEntity;
import com.cpe.sumulationserver.services.TruckService;

import mil.nga.sf.geojson.FeatureCollection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/truck")
public class TruckController {
    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @CrossOrigin("*")
    @GetMapping("/geo/point")
    private ResponseEntity<FeatureCollection> getTrucksGeoPoint() {
        return ResponseEntity.ok(truckService.getAllTrucksGeoPoint());
    }

    @PostMapping("/add")
    public void addTruck(@RequestBody TruckEntity truckEntity) {
        truckService.addTruck(truckEntity);
    }

    @CrossOrigin("*")
    @PutMapping("/edit")
    public void editTruck(@RequestBody TruckEntity truckEntity) {
        truckService.saveTruck(truckEntity);
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/{id}")
    public void deleteTruck(@PathVariable("id") int truckId) {
        truckService.deleteTruck(truckId);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TruckEntity>> getTrucks() {
        return ResponseEntity.ok(truckService.getTrucks());
    }

    @CrossOrigin("*")
    @GetMapping("/get/{id}")
    public ResponseEntity<TruckEntity> getTruck(@PathVariable("id") int truckId) {
        return ResponseEntity.ok(truckService.getTruck(truckId));
    }
}