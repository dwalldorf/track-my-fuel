package com.dwalldorf.fuel.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cars")
public class Car implements HasUser, Serializable {

    private static final String OBJECT_TYPE = "Car";

    @Id
    @GeneratedValue
    @Column(name = "car_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private String modelName;

    @Column
    private String year;

    @Column
    private String licensePlate;

    @OneToMany(mappedBy = "car")
    private List<Expense> expenses;

    @Override
    public String getObjectType() {
        return OBJECT_TYPE;
    }

    public Long getId() {
        return id;
    }

    public Car setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Car setUser(User user) {
        this.user = user;
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