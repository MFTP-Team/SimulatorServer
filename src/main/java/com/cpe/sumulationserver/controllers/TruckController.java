package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.TruckEntity;
import com.cpe.sumulationserver.services.TruckService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/intervention")
public class TruckController {
    private final TruckService truckService;

    public TruckController(TruckService truckService) {
        this.truckService = truckService;
    }

    @PostMapping("/add")
    public void addtruck(@RequestBody TruckEntity truckEntity) {
        truckService.addTruck(truckEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deletetruck(@PathVariable("id") int truckId) {
        truckService.deleteTruck(truckId);
    }

    @GetMapping("/get/{id}")
    public void gettruck(@PathVariable("id") int truckId) {
        truckService.getTruck(truckId);
    }
}