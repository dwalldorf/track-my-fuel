package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.model.Expense;
import com.dwalldorf.fuel.model.ExpenseType;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.repository.ExpenseRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CarService carService;

    @Inject
    public ExpenseService(ExpenseRepository expenseRepository, CarService carService) {
        this.expenseRepository = expenseRepository;
        this.carService = carService;
    }

    public void calculateRefuelingExpenses(Refueling refueling) {
        final Float liters = refueling.getLiters();
        final Float cost = refueling.getCost();

        if (liters == null || cost == null) {
            return;
        }

        Expense expense = new Expense()
                .setCar(refueling.getCar())
                .setType(ExpenseType.REFUELING)
                .setCost(liters * cost)
                .setRefueling(refueling);

        expenseRepository.save(expense);
    }
}