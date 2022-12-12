package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.SensorEntity;
import com.cpe.sumulationserver.services.SensorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensor")
public class SensorController {
    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public void addsensor(@RequestBody SensorEntity sensorEntity) {
        sensorService.addSensor(sensorEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deletesensor(@PathVariable("id") int sensorId) {
        sensorService.deleteSensor(sensorId);
    }

    @GetMapping("/get/{id}")
    public void getsensor(@PathVariable("id") int sensorId) {
        sensorService.getSensor(sensorId);
    }

    @PutMapping("/edit")
    public void editsensor(@RequestBody SensorEntity sensorEntity) {
        sensorService.editSensor(sensorEntity);
    }
}
