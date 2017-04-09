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

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}