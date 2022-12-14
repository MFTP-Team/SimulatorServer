package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.services.FireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fire")
public class FireController {
    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
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

    @GetMapping("/get/{id}")
    public ResponseEntity<FireEntity> getFire(@PathVariable("id") int fireId) {
        return ResponseEntity.ok(fireService.getFire(fireId));
    }

    @PutMapping("/edit")
    public ResponseEntity<FireEntity> editFire(@RequestBody FireEntity fireEntity) {
        return ResponseEntity.ok(fireService.editFire(fireEntity));
    }
}
