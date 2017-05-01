package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.ExpenseService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExpenseController {

    private static final String ROUTE_PREFIX = "/car/{carId}/expenses";

    private static final String VIEW_PREFIX = "/expense/";
    private static final String VIEW_LIST = VIEW_PREFIX + "list";

    private final ExpenseService expenseService;
    private final CarService carService;

    @Inject
    public ExpenseController(ExpenseService expenseService, CarService carService) {
        this.expenseService = expenseService;
        this.carService = carService;
    }

    @GetMapping(ROUTE_PREFIX)
    public ModelAndView listPage(@PathVariable Long carId) {
        Car car = carService.findById(carId);
        if (car == null) {
            throw new NotFoundException();
        }

        ModelAndView mav = new ModelAndView(VIEW_LIST);
        mav.addObject("expenses", expenseService.findByCar(car));

        return mav;
    }
}