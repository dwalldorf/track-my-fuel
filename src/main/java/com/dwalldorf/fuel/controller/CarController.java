package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.UserService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    private static final String ROUTE_PREFIX = "/cars";
    private static final String ROUTE_PAGE_ADD = ROUTE_PREFIX + "/add";

    private static final String VIEW_PREFIX = "/car/";
    private static final String VIEW_LIST = VIEW_PREFIX + "/list";
    private static final String VIEW_EDIT = VIEW_PREFIX + "/edit";

    private final CarService carService;
    private final UserService userService;

    @Inject
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @GetMapping(ROUTE_PREFIX)
    public String indexAction() {
        return listAction();
    }


    public String listAction() {
        return VIEW_LIST;
    }
}