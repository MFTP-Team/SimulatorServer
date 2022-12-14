package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.SensorEntity;
import com.cpe.sumulationserver.repository.SensorRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public SensorEntity addSensor(SensorEntity sensorEntity) {
        return this.sensorRepository.save(sensorEntity);
    }

    public void deleteSensor(int sensorId) {
        this.sensorRepository.deleteById(sensorId);
    }

    public SensorEntity getSensor(int sensorId) {
        return this.sensorRepository.findById(sensorId);
    }

    public SensorEntity editSensor(SensorEntity sensorEntity) {
        return this.sensorRepository.save(sensorEntity);
    }
}
