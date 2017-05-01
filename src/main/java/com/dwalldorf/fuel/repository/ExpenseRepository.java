package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.Expense;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

    List<Expense> findByCar(Car car);
}