package com.cpe.sumulationserver.model;

import jakarta.persistence.*;

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
    private Double longitude;
    @Basic
    @Column(name = "latitude")
    private Double latitude;
    @Basic
    @Column(name = "intensity")
    private Integer intensity;


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

    public Integer getIntensity() {
        return intensity;
    }

    public void setIntensity(Integer intensity) {
        this.intensity = intensity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FireEntity that = (FireEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(longitude, that.longitude) && Objects.equals(latitude, that.latitude) && Objects.equals(intensity, that.intensity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, longitude, latitude, intensity);
    }
}
