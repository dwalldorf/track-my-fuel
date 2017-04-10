package com.dwalldorf.fuel.form.car;

import com.dwalldorf.fuel.form.ThymeleafForm;
import com.dwalldorf.fuel.model.Car;

public class CarForm implements ThymeleafForm<Car, CarForm> {

    private String id;

    private String userId;

    private String manufacturer;

    private String modelName;

    private String year;

    private String licensePlate;

    @Override
    public Car toModel() {
        return new Car()
                .setId(getId())
                .setUserId(getUserId())
                .setManufacturer(getManufacturer())
                .setModelName(getModelName())
                .setYear(getYear())
                .setLicensePlate(getLicensePlate());
    }

    @Override
    public CarForm fromModel(Car model) {
        if (model == null) {
            return null;
        }

        return new CarForm()
                .setId(model.getId())
                .setUserId(model.getUserId())
                .setManufacturer(model.getManufacturer())
                .setModelName(model.getModelName())
                .setYear(model.getYear())
                .setLicensePlate(model.getLicensePlate());
    }

    public String getId() {
        return id;
    }

    public CarForm setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public CarForm setUserId(String userId) {
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