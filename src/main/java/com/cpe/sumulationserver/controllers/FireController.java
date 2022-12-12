package com.cpe.sumulationserver.controllers;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.services.FireService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fire")
public class FireController {
    private final FireService fireService;

    public FireController(FireService fireService) {
        this.fireService = fireService;
    }

    @PostMapping("/add")
    public void addfire(@RequestBody FireEntity fireEntity) {
        fireService.addFire(fireEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void deletefire(@PathVariable("id") int fireId) {
        fireService.deleteFire(fireId);
    }

    @GetMapping("/get/{id}")
    public void getfire(@PathVariable("id") int fireId) {
        fireService.getFire(fireId);
    }

    @PutMapping("/edit")
    public void editfire(@RequestBody FireEntity fireEntity) {
        fireService.editFire(fireEntity);
    }
}
