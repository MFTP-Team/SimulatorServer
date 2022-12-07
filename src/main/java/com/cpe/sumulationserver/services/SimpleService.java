package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.dto.SimpleEntityDTO;
import com.cpe.sumulationserver.model.SimpleEntity;
import com.cpe.sumulationserver.repository.SimpleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleService {

    @Autowired
    private SimpleRepository simpleRepository;

    public SimpleEntityDTO getSimpleEntityDTO(String name) {
        List<SimpleEntity> list = simpleRepository.findAll();
        return null;
    }
}
