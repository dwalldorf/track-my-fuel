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
        return new RefuelingForm();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public Float getLiters() {
        return liters;
    }

    public void setLiters(Float liters) {
        this.liters = liters;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
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

    public void setDate(DateTime date) {
        this.date = date;
    }

    public static DateTimeFormatter getFORMATTER() {
        return FORMATTER;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}