package com.cpe.sumulationserver.repository;

import com.cpe.sumulationserver.model.AlertEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface AlertRepository extends Repository<AlertEntity, Integer> {
    AlertEntity findById(Integer id);
    AlertEntity save(AlertEntity alertEntity);
    public void delete(AlertEntity alertEntity);
    AlertEntity deleteById(Integer id);
    List<AlertEntity> findAll();
}