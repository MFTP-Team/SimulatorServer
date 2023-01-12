package com.cpe.sumulationserver.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "sensor", schema = "public", catalog = "simulator")
public class SensorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "longitude")
    private Double longitude;
    @Basic
    @Column(name = "latitude")
    private Double latitude;

    @Basic
    @Column(name = "radius")
    private Integer radius;

    @OneToMany(mappedBy = "idSensor")
    private Collection<AlertEntity> alertsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Integer getRadius() {
        return radius;
    }

    public void setRadius(Integer radius) {
        this.radius = radius;
    }

    public Collection<AlertEntity> getAlertsById() {
        return alertsById;
    }

    public void setAlertsById(Collection<AlertEntity> alertsById) {
        this.alertsById = alertsById;
    }
}
