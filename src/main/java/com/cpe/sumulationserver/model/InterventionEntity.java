package com.cpe.sumulationserver.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "intervention", schema = "public", catalog = "simulator")
public class InterventionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "date")
    private Timestamp date;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "id_fire")
    private Integer idFire;
    @ManyToMany
    @JoinTable(
            name = "fireman_intervention",
            joinColumns = @JoinColumn(name = "id_fireman"),
            inverseJoinColumns = @JoinColumn(name = "id_intervention"))
    private Collection<FiremanEntity> firemen;
    @ManyToMany
    @JoinTable(
            name = "truck_intervention",
            joinColumns = @JoinColumn(name = "id_truck"),
            inverseJoinColumns = @JoinColumn(name = "id_intervention"))
    private Collection<TruckEntity> trucks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getIdFire() {
        return idFire;
    }

    public void setIdFire(Integer idFire) {
        this.idFire = idFire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InterventionEntity that = (InterventionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(date, that.date) && Objects.equals(status, that.status) && Objects.equals(idFire, that.idFire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, status, idFire);
    }

    public Collection<FiremanEntity> getFiremen() {
        return firemen;
    }

    public void setFiremen(Collection<FiremanEntity> firemmen) {
        this.firemen = firemmen;
    }

    public Collection<TruckEntity> getTrucks() {
        return trucks;
    }

    public void setTrucks(Collection<TruckEntity> trucks) {
        this.trucks = trucks;
    }
}
