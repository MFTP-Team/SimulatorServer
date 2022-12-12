package com.cpe.sumulationserver.model;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor", schema = "public", catalog = "simulator")
public class SensorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "longitude")
    private String longitude;
    @Basic
    @Column(name = "latitude")
    private String latitude;
}
