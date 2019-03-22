package com.rm.inventorytracking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RawMaterial {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "code", nullable = false, updatable = false, unique = true)
    private String inventoryCode;

    @Column(name = "type", nullable = false)
    private String type;
    
    @Column(name = "unit", nullable = false)
    private String unit;
}
