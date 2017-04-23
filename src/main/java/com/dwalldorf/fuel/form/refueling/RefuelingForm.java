package com.dwalldorf.fuel.form.refueling;

import java.io.Serializable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class RefuelingForm implements Serializable {

    private final static DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("YYYY-MM-dd hh:mm");

    private Long id;

    private Long userId;

    private Long carId;

    private Long kilometers;

    private Float liters;

    private Float cost;

    @org.springframework.format.annotation.DateTimeFormat(pattern = "YYYY-MM-dd")
    private DateTime date;

    private String comment;

    public Long getId() {
        return id;
    }

    public RefuelingForm setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public RefuelingForm setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Long getCarId() {
        return carId;
    }

    public RefuelingForm setCarId(Long carId) {
        this.carId = carId;
        return this;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public RefuelingForm setKilometers(Long kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public Float getLiters() {
        return liters;
    }

    public RefuelingForm setLiters(Float liters) {
        this.liters = liters;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public RefuelingForm setCost(Float cost) {
        this.cost = cost;
        return this;
    }

    public DateTime getDate() {
        return date;
    }

    @SuppressWarnings("unused")
    public String getDateString() {
        if (date == null) {
            return null;
        }
        return FORMATTER.print(new DateTime(date));
    }

    public RefuelingForm setDate(DateTime date) {
        this.date = date;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public RefuelingForm setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @SuppressWarnings("unused")
    public String getCostPerKilometer() {
        if (cost == null || liters == null) {
            return "";
        }

        return String.format("%.4g%n€", cost / liters);
    }
}