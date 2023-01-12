package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.AlertEntity;
import com.cpe.sumulationserver.services.AlertService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/alert")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @CrossOrigin("*")
    @PostMapping("/add")
    public ResponseEntity<AlertEntity> addAlert(@RequestBody AlertEntity alertEntity) {
        return ResponseEntity.ok(alertService.addAlert(alertEntity));
    }

    @CrossOrigin("*")
    @PutMapping("/edit")
    public void editAlert(@RequestBody AlertEntity alertEntity) {
        alertService.editAlert(alertEntity);
    }

    @CrossOrigin("*")
    @DeleteMapping("/delete/{id}")
    public void deleteAlert(@PathVariable("id") int alertId) {
        alertService.deleteAlert(alertId);
    }

    @CrossOrigin("*")
    @GetMapping("/get/{id}")
    public void getAlert(@PathVariable("id") int alertId) {
        alertService.getAlert(alertId);
    }

    @CrossOrigin("*")
    @GetMapping("/get")
    public ResponseEntity<List<AlertEntity>> getAlert() {
        return ResponseEntity.ok(alertService.getAlerts());
    }
}
