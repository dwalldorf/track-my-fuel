package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.UserService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

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

    @ModelAttribute("cars")
    public List<Car> cars() {
        return carService.findByUserId(userService.getCurrentUserId());
    }

    @GetMapping(ROUTE_PREFIX)
    public String indexAction() {
        return listPage();
    }


    public String listPage() {
        return VIEW_LIST;
    }

    @GetMapping(ROUTE_PAGE_ADD)
    public ModelAndView addPage() {
        ModelAndView mav = new ModelAndView(VIEW_EDIT);
        mav.addObject("carForm", new CarForm());

        return mav;
    }
}