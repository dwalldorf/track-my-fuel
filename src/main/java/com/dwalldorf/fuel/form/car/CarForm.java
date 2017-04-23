package com.dwalldorf.fuel.form.car;

import java.io.Serializable;
import javax.inject.Inject;

public class CarForm implements Serializable {

    private Long id;

    private Long userId;

    private String manufacturer;

    private String modelName;

    private String year;

    private String licensePlate;

    @Inject
    public CarForm() {
    }

    public Long getId() {
        return id;
    }

    public CarForm setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public CarForm setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public CarForm setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public String getModelName() {
        return modelName;
    }

    public CarForm setModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    public String getYear() {
        return year;
    }

    public CarForm setYear(String year) {
        this.year = year;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public CarForm setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }
}