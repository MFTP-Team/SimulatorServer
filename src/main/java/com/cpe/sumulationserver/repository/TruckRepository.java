package com.cpe.sumulationserver.repository;
import com.cpe.sumulationserver.model.TruckEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface TruckRepository extends Repository<TruckEntity, Integer> {
    TruckEntity findById(Integer id);
    TruckEntity save(TruckEntity truckEntity);
    TruckEntity deleteById(Integer id);
    List<TruckEntity> findAll();
}
