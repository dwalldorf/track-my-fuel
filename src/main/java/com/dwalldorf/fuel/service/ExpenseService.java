package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.Expense;
import com.dwalldorf.fuel.model.ExpenseType;
import com.dwalldorf.fuel.repository.ExpenseRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CarService carService;

    @Inject
    public ExpenseService(ExpenseRepository expenseRepository, CarService carService) {
        this.expenseRepository = expenseRepository;
        this.carService = carService;
    }

    @Transactional
    public void calculateRefuelingExpenses(RefuelingForm refueling) {
        final Car car = carService.findById(refueling.getCarId());

        Expense expense = new Expense()
                .setCar(car)
                .setType(ExpenseType.REFUELING)
                .setCost(refueling.getCost())
                .setKilometers(refueling.getKilometers());

        expenseRepository.save(expense);
    }

    @Transactional
    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    public List<Expense> findByCar(Car car) {
        return expenseRepository.findByCar(car);
    }

    public Expense findById(Long id) {
        return expenseRepository.findOne(id);
    }
}