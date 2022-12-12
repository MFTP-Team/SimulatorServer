package com.cpe.sumulationserver.model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "fire", schema = "public", catalog = "simulator")
public class FireEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "longitude")
    private Float longitude;
    @Basic
    @Column(name = "latitude")
    private Float latitude;
    @Basic
    @Column(name = "intensity")
    private Integer intensity;
    @Basic
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "idFire")
    private Collection<AlertEntity> alertsById;
    @OneToMany(mappedBy = "idFire")
    private Collection<InterventionEntity> interventionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireEntity that = (FireEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude) && Objects.equals(intensity, that.intensity) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude, intensity, status);
    }

    public Collection<AlertEntity> getAlertsById() {
        return alertsById;
    }

    public void setAlertsById(Collection<AlertEntity> alertsById) {
        this.alertsById = alertsById;
    }

    public Collection<InterventionEntity> getInterventionsById() {
        return interventionsById;
    }

    public void setInterventionsById(Collection<InterventionEntity> interventionsById) {
        this.interventionsById = interventionsById;
    }
}
