package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.repository.FireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FireService {
    private final FireRepository fireRepository;
    private final SensorService sensorService;

    public FireService(FireRepository fireRepository, SensorService sensorService) {
        this.fireRepository = fireRepository;
        this.sensorService = sensorService;
    }

    public FireEntity addFire(FireEntity fireEntity) {
        log.info("Création d'un feu en {} {}", fireEntity.getLongitude(), fireEntity.getLatitude());
        FireEntity fire = this.fireRepository.save(fireEntity);
        this.sensorService.triggerFire(fire);
        return fire;
    }

    public void deleteFire(int fireId) {
        this.fireRepository.deleteById(fireId);
    }

    public FireEntity getFire(int fireId) {
        return this.fireRepository.findById(fireId);
    }

    public FireEntity editFire(FireEntity fireEntity) {
        return this.fireRepository.save(fireEntity);
    }

    public List<FireEntity> getFires() {
        return this.fireRepository.findAll();
    }
}
