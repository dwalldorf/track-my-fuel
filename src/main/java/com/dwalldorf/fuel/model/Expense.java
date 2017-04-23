package com.dwalldorf.fuel.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expenses",indexes = @Index(columnList = "type"))
public class Expense implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "expense_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @Column
    private ExpenseType type;

    /**
     * Only if related to refueling
     */
    @OneToOne
    @JoinColumn(name = "refueling_id")
    private Refueling refueling;

    @Column
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

    public Refueling getRefueling() {
        return refueling;
    }

    public Expense setRefueling(Refueling refueling) {
        this.refueling = refueling;
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