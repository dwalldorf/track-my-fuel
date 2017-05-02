package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.Expense;
import com.dwalldorf.fuel.model.ExpenseType;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.RefuelingRepository;
import java.util.List;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RefuelingService {

    private final RefuelingRepository refuelingRepository;

    private final ExpenseService expenseService;

    private final UserService userService;

    private final CarService carService;

    @Inject
    public RefuelingService(RefuelingRepository refuelingRepository, ExpenseService expenseService, UserService userService, CarService carService) {
        this.refuelingRepository = refuelingRepository;
        this.expenseService = expenseService;
        this.userService = userService;
        this.carService = carService;
    }

    public List<Refueling> findAllByUser(User user) {
        return refuelingRepository.findAllByUser(user);
    }

    @Transactional
    public void save(Refueling refueling) {
        if (refueling.getExpense() != null) {
            expenseService.save(refueling.getExpense());
        }
        refuelingRepository.save(refueling);
    }

    public Refueling findById(Long id) {
        return refuelingRepository.findOne(id);
    }

    @Transactional
    public void delete(Refueling refueling) {
        refuelingRepository.delete(refueling);
    }

    public Refueling toModel(RefuelingForm form) {
        Car car = null;
        if (form.getCarId() != null) {
            car = carService.findById(form.getCarId());
        }

        User user = null;
        if (form.getUserId() != null) {
            user = userService.findById(form.getUserId());
        }

        final DateTime date = form.getDate();
        Expense expense = new Expense()
                .setType(ExpenseType.REFUELING)
                .setCar(car)
                .setDate(date)
                .setKilometers(form.getKilometers())
                .setCost(form.getCost());

        return new Refueling()
                .setId(form.getId())
                .setUser(user)
                .setLiters(form.getLiters())
                .setDate(date)
                .setCar(car)
                .setExpense(expense)
                .setComment(form.getComment());
    }

    public RefuelingForm fromModel(Refueling model) {
        if (model == null) {
            return null;
        }

        RefuelingForm form = new RefuelingForm()
                .setId(model.getId())
                .setUserId(model.getUser().getId())
                .setCarId(model.getCar().getId())
                .setLiters(model.getLiters())
                .setComment(model.getComment());

        if (model.getUser() != null) {
            form.setUserId(model.getUser().getId());
        }
        if (model.getCar() != null) {
            form.setCarId(model.getCar().getId());
        }
        if (model.getExpense() != null) {
            form.setKilometers(model.getExpense().getKilometers())
                .setCost(model.getExpense().getCost());
        }

        return form;
    }
}