package com.cpe.sumulationserver.services;

import com.cpe.sumulationserver.model.TruckEntity;
import com.cpe.sumulationserver.repository.TruckRepository;
import com.cpe.sumulationserver.util.GeoJsonUtil;

import mil.nga.sf.geojson.Feature;
import mil.nga.sf.geojson.FeatureCollection;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruckService {
    private final TruckRepository truckRepository;

    public TruckService(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }
    public TruckEntity saveTruck(TruckEntity truckEntity) {
        return this.truckRepository.save(truckEntity);
    }

    public TruckEntity getTruck(int truckId) {
        return this.truckRepository.findById(truckId);
    }

    public void deleteTruck(int truckId) {
        this.truckRepository.deleteById(truckId);
    }

    public List<TruckEntity> getTrucks() {
        return this.truckRepository.findAll();
    }

    public TruckEntity addTruck(TruckEntity truckEntity) {
        return this.truckRepository.save(truckEntity);
    }

    public FeatureCollection getAllTrucksGeoPoint() {
        List<TruckEntity> list = this.truckRepository.findAll();
        FeatureCollection featureCollection = new FeatureCollection();
        for (TruckEntity truck : list) {
            Feature pointFeature = GeoJsonUtil.getPointFromTruck(truck);
            featureCollection.addFeature(pointFeature);
        }
        return featureCollection;
    }
}
