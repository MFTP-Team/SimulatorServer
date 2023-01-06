package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.services.FireService;

import mil.nga.sf.geojson.FeatureCollection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fire")
public class FireController {
    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @CrossOrigin("*")
    @GetMapping("/geo/polygon")
    private ResponseEntity<FeatureCollection> getStationGeoPolygon() {
        return ResponseEntity.ok(fireService.getAllFiresGeoPolygon());
    }

    @CrossOrigin("*")
    @GetMapping("/geo/point")
    private ResponseEntity<FeatureCollection> getStationGeoPoint() {
        return ResponseEntity.ok(fireService.getAllFiresGeoPoint());
    }

    @PostMapping("/add")
    public ResponseEntity<FireEntity> addFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.addFire(fireEntity));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletefire(@PathVariable("id") int fireId) {
        fireService.deleteFire(fireId);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin("*")
    @GetMapping("/get/{id}")
    public ResponseEntity<FireEntity> getFire(@PathVariable("id") int fireId) {
        return ResponseEntity.ok(fireService.getFire(fireId));
    }

    @PutMapping("/edit")
    public ResponseEntity<FireEntity> editFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.editFire(fireEntity));
    }
}
