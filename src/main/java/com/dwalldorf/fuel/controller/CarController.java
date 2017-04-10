package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.exception.NotFoundException;
import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.service.CarService;
import com.dwalldorf.fuel.service.UserService;
import com.dwalldorf.fuel.util.RouteUtil;
import java.util.LinkedList;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarController {

    private static final String ROUTE_PREFIX = "/cars";
    private static final String ROUTE_PAGE_ADD = ROUTE_PREFIX + "/add";
    private static final String ROUTE_PAGE_EDIT = ROUTE_PREFIX + "/{id}/edit";

    private static final String VIEW_PREFIX = "/car/";
    private static final String VIEW_LIST = VIEW_PREFIX + "list";
    private static final String VIEW_EDIT = VIEW_PREFIX + "edit";

    private final CarService carService;
    private final UserService userService;

    private static final List<Integer> yearValues;

    static {
        final int oldestYearPossible = 1880;
        final int newestYearPossible = new DateTime().getYear() + 1;

        yearValues = new LinkedList<>();
        for (int i = newestYearPossible; i >= oldestYearPossible; i--) {
            yearValues.add(i);
        }
    }

    @Inject
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @ModelAttribute("cars")
    public List<Car> getCars() {
        return carService.findByUserId(userService.getCurrentUserId());
    }

    @ModelAttribute("yearValues")
    public List<Integer> getYearValues() {
        return yearValues;
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

    @GetMapping(ROUTE_PAGE_EDIT)
    public ModelAndView editPage(@PathVariable String id) {
        Car car = carService.findById(id);
        if (car == null) {
            throw new NotFoundException();
        }
        userService.verifyOwner(car);

        ModelAndView mav = new ModelAndView(VIEW_EDIT);
        mav.addObject("carForm", new CarForm().fromModel(car));
        return mav;
    }

    @PostMapping(ROUTE_PREFIX)
    public String saveAction(@ModelAttribute @Valid CarForm carForm) {
        Car car = carForm.toModel();

        if (car.getId() != null) {
            Car persistedCar = carService.findById(car.getId());
            if (persistedCar == null) {
                throw new NotFoundException();
            }
            userService.verifyOwner(persistedCar);
        } else {
            car.setUserId(userService.getCurrentUserId());
        }

        carService.save(car);
        return RouteUtil.redirectString(ROUTE_PREFIX);
    }
}