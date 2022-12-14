package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.AlertEntity;
import com.cpe.sumulationserver.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertService {
    private final FireService fireService;
    private final AlertRepository alertRepository;

    public AlertService(FireService fireService, AlertRepository alertRepository) {
        this.fireService = fireService;
        this.alertRepository = alertRepository;
    }

    public AlertEntity addAlert(AlertEntity alertEntity) {
        return this.alertRepository.save(alertEntity);
    }

    public AlertEntity editAlert(AlertEntity alertEntity) {
        return this.alertRepository.save(alertEntity);
    }

    public void deleteAlert(int alertId) {
        this.alertRepository.deleteById(alertId);
    }

    public AlertEntity getAlert(int alertId) {
        return this.alertRepository.findById(alertId);
    }

    public List<AlertEntity> getAlerts() {
        return this.alertRepository.findAll();
    }
}
