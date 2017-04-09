package com.dwalldorf.fuel.form.refueling;

import com.dwalldorf.fuel.model.Refueling;
import java.io.Serializable;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class RefuelingForm implements Serializable {

    private final static DateTimeFormatter FORMATTER = DateTimeFormat.forPattern("YYYY-MM-dd hh:mm");

    private String id;

    private String userId;

    private Long kilometers;

    private Float liters;

    private Float cost;

    @org.springframework.format.annotation.DateTimeFormat(pattern = "YYYY-MM-dd")
    private DateTime date;

    private String comment;

    public Refueling toModel() {
        return new Refueling()
                .setId(getId())
                .setUserId(getUserId())
                .setKilometers(getKilometers())
                .setLiters(getLiters())
                .setCost(getCost())
                .setDate(getDate())
                .setComment(getComment());
    }

    public static RefuelingForm fromModel(Refueling model) {
        if (model == null) {
            return null;
        }

        return new RefuelingForm()
                .setId(model.getId())
                .setUserId(model.getUserId())
                .setKilometers(model.getKilometers())
                .setLiters(model.getLiters())
                .setCost(model.getCost())
                .setComment(model.getComment());
    }

    public String getId() {
        return id;
    }

    public RefuelingForm setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public RefuelingForm setUserId(String userId) {
        this.userId = userId;
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
}