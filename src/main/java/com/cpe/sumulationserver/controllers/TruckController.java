package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.TruckEntity;
import com.cpe.sumulationserver.services.TruckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RestController
@RequestMapping("/api/intervention")
public class TruckController {
    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @PostMapping("/add")
    public void addTruck(@RequestBody TruckEntity truckEntity) {
        truckService.addTruck(truckEntity);
    }

    @PutMapping("/edit")
    public void editTruck(@RequestBody TruckEntity truckEntity) {
        truckService.saveTruck(truckEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTruck(@PathVariable("id") int truckId) {
        truckService.deleteTruck(truckId);
    }

    @GetMapping("/get")
    public ResponseEntity<List<TruckEntity>> getTrucks() {
        return ResponseEntity.ok(truckService.getTrucks());
    }

    @GetMapping("/get/{id}")
    public void getTruck(@PathVariable("id") int truckId) {
        truckService.getTruck(truckId);
    }
}