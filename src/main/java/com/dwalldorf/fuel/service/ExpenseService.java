package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.form.expense.ExpenseForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.Expense;
import com.dwalldorf.fuel.model.ExpenseType;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.ExpenseRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    private final CarService carService;

    private final UserService userService;

    @Inject
    public ExpenseService(ExpenseRepository expenseRepository, CarService carService, UserService userService) {
        this.expenseRepository = expenseRepository;
        this.carService = carService;
        this.userService = userService;
    }

    @Transactional
    public Expense save(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> findByCar(Car car) {
        return expenseRepository.findByCar(car);
    }

    public Expense findById(Long id) {
        return expenseRepository.findOne(id);
    }

    public Expense toModel(ExpenseForm form) {
        User user = null;
        if (form.getId() != null) {
            Expense expense = findById(form.getId());
            user = expense.getUser();
        }

        ExpenseType type;
        try {
            type = ExpenseType.valueOf(form.getType());
        } catch (IllegalArgumentException ex) {
            type = ExpenseType.OTHER;
        }

        Car car = null;
        if (form.getCarId() != null) {
            car = carService.findById(form.getCarId());
        }

        return new Expense()
                .setId(form.getId())
                .setUser(user)
                .setType(type)
                .setDate(form.getDate())
                .setCar(car)
                .setKilometers(form.getKilometers())
                .setCost(form.getCost());
    }
}