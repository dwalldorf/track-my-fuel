package com.dwalldorf.fuel.model;

import java.io.Serializable;
import org.mongodb.morphia.annotations.Id;

public class Refueling implements HasUserId, Serializable {

    private static final String OBJECT_TYPE = "Refueling";

    @Id
    private String id;

    private String userId;

    private Long milage;

    private Double liters;

    private Double cost;

    @Override
    public String getObjectType() {
        return OBJECT_TYPE;
    }

    @Override
    public String getId() {
        return id;
    }

    public Refueling setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public Refueling setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Long getMilage() {
        return milage;
    }

    public Refueling setMilage(Long milage) {
        this.milage = milage;
        return this;
    }

    public Double getLiters() {
        return liters;
    }

    public Refueling setLiters(Double liters) {
        this.liters = liters;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public Refueling setCost(Double cost) {
        this.cost = cost;
        return this;
    }
}