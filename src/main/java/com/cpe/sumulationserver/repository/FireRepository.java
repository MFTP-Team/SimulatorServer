package com.cpe.sumulationserver.repository;

import com.cpe.sumulationserver.model.FireEntity;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface FireRepository extends Repository<FireEntity, Integer> {
    FireEntity findById(Integer id);
    FireEntity save(FireEntity fireEntity);
    void delete(FireEntity fireEntity);
    FireEntity deleteById(Integer id);

    List<FireEntity> findAll();
}
