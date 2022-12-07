package com.cpe.sumulationserver.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_name")
public class SimpleEntity {
    @Id
    @Column(name = "example_id")
    private long id;

    @Column(name = "example_name")
    private String name;
}
