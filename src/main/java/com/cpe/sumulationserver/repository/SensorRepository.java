package com.cpe.sumulationserver.repository;

import com.cpe.sumulationserver.model.SensorEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface SensorRepository extends Repository<SensorEntity, Integer> {
    SensorEntity findById(Integer id);
    SensorEntity save(SensorEntity sensorEntity);
    void delete(SensorEntity fireEntity);
    SensorEntity deleteById(Integer id);

    List<SensorEntity> findAll();
}
