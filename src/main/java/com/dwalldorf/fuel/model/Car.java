package com.dwalldorf.fuel.model;

import java.io.Serializable;
import org.mongodb.morphia.annotations.Id;

public class Car implements HasUserId, Serializable {

    private static final String OBJECT_TYPE = "Car";

    @Id
    private String id;

    private String userId;

    private String manufacturer;

    private String modelName;

    private String year;

    private String licensePlate;

    @Override
    public String getObjectType() {
        return OBJECT_TYPE;
    }

    @Override
    public String getId() {
        return id;
    }

    public Car setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public Car setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Car setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public Car setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getYear() {
        return year;
    }

    public Car setYear(String year) {
        this.year = year;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Car setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
}