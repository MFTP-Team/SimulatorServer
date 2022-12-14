package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.FireEntity;
import com.cpe.sumulationserver.repository.FireRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireService {
    private final FireRepository fireRepository;

    public FireService(FireRepository fireRepository) {
        this.fireRepository = fireRepository;
    }

    public FireEntity addFire(FireEntity fireEntity) {
        return this.fireRepository.save(fireEntity);
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
