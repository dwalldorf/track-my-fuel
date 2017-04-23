package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
}