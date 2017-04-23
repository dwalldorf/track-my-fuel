package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.form.refueling.RefuelingForm;
import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.RefuelingRepository;
import java.util.List;
import javax.inject.Inject;
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
        refueling = refuelingRepository.save(refueling);
        expenseService.calculateRefuelingExpenses(refueling);
    }

    public Refueling findById(Long id) {
        return refuelingRepository.findOne(id);
    }

    @Transactional
    public void delete(Refueling refueling) {
        refuelingRepository.delete(refueling);
    }

    public Refueling toModel(RefuelingForm form) {
        Refueling refueling = new Refueling()
                .setId(form.getId())
                .setKilometers(form.getKilometers())
                .setLiters(form.getLiters())
                .setCost(form.getCost())
                .setDate(form.getDate())
                .setComment(form.getComment());

        if (form.getUserId() != null) {
            refueling.setUser(userService.findById(form.getUserId()));
        }
        if (form.getCarId() != null) {
            refueling.setCar(carService.findById(form.getCarId()));
        }

        return refueling;
    }

    public RefuelingForm fromModel(Refueling model) {
        if (model == null) {
            return null;
        }

        RefuelingForm form = new RefuelingForm()
                .setId(model.getId())
                .setKilometers(model.getKilometers())
                .setLiters(model.getLiters())
                .setCost(model.getCost())
                .setComment(model.getComment());

        if (model.getUser() != null) {
            form.setUserId(model.getUser().getId());
        }
        if (model.getCar() != null) {
            form.setCarId(model.getCar().getId());
        }

        return form;
    }
}