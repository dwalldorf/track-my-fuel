package com.dwalldorf.fuel.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "refuelings", indexes = @Index(columnList = "date, kilometers"))
public class Refueling implements HasUser, Serializable {

    private static final String OBJECT_TYPE = "Refueling";

    @Id
    @GeneratedValue
    @Column(name = "refueling_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime date;

    @Column
    private Long kilometers;

    @Column
    private Float liters;

    @Column
    private Float cost;

    @Column
    private String comment;

    @Override
    public String getObjectType() {
        return OBJECT_TYPE;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Refueling setId(Long id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Refueling setUser(User user) {
        this.user = user;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Refueling setCar(Car car) {
        this.car = car;
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