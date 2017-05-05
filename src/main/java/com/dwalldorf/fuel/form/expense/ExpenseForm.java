package com.dwalldorf.fuel.form.expense;

import java.io.Serializable;
import org.joda.time.DateTime;

public class ExpenseForm implements Serializable {

    private Long id;

    private Long carId;

    private String type;

    private DateTime date;

    private Long kilometers;

    private Float cost;

    public Long getId() {
        return id;
    }

    public ExpenseForm setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public ExpenseForm setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public String getType() {
        return type;
    }

    public ExpenseForm setType(String type) {
        this.type = type;
        return this;
    }

    public DateTime getDate() {
        return date;
    }

    public ExpenseForm setDate(DateTime date) {
        this.date = date;
        return this;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public ExpenseForm setKilometers(Long kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public ExpenseForm setCost(Float cost) {
        this.cost = cost;
        return this;
    }
}