package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.AlertEntity;
import com.cpe.sumulationserver.services.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alert")
public class AlertController {
    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    @PostMapping("/add")
    public ResponseEntity<AlertEntity> addAlert(@RequestBody AlertEntity alertEntity) {
        return ResponseEntity.ok(alertService.addAlert(alertEntity));
    }

    @PutMapping("/edit")
    public void editAlert(@RequestBody AlertEntity alertEntity) {
        alertService.editAlert(alertEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAlert(@PathVariable("id") int alertId) {
        alertService.deleteAlert(alertId);
    }

    @GetMapping("/get/{id}")
    public void getAlert(@PathVariable("id") int alertId) {
        alertService.getAlert(alertId);
    }
}
