package com.dwalldorf.fuel.model;

import java.io.Serializable;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.Id;

public class Refueling implements HasUserId, Serializable {

    private static final String OBJECT_TYPE = "Refueling";

    @Id
    private String id;

    private String userId;

    private DateTime date;

    private Long kilometers;

    private Float liters;

    private Float cost;

    private String comment;

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

    public DateTime getDate() {
        return date;
    }

    public Refueling setDate(DateTime date) {
        this.date = date;
        return this;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public Refueling setKilometers(Long kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public Float getLiters() {
        return liters;
    }

    public Refueling setLiters(Float liters) {
        this.liters = liters;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public Refueling setCost(Float cost) {
        this.cost = cost;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Refueling setComment(String comment) {
        this.comment = comment;
        return this;
    }
}