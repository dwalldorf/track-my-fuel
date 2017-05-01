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
@Table(name = "expenses", indexes = @Index(columnList = "type, date, kilometers"))
public class Expense implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "expense_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @Column(nullable = false)
    private ExpenseType type;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private DateTime date;

    @Column(nullable = false)
    private Long kilometers;

    @Column(nullable = false)
    private Float cost;

    public Long getId() {
        return id;
    }

    public Expense setId(Long id) {
        this.id = id;
        return this;
    }

    public Car getCar() {
        return car;
    }

    public Expense setCar(Car car) {
        this.car = car;
        return this;
    }

    public ExpenseType getType() {
        return type;
    }

    public Expense setType(ExpenseType type) {
        this.type = type;
        return this;
    }

    public DateTime getDate() {
        return date;
    }

    public Expense setDate(DateTime date) {
        this.date = date;
        return this;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public Expense setKilometers(Long kilometers) {
        this.kilometers = kilometers;
        return this;
    }

    public Float getCost() {
        return cost;
    }

    public Expense setCost(Float cost) {
        this.cost = cost;
        return this;
    }
}