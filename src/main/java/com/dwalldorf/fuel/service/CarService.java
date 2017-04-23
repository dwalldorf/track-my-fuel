package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.form.car.CarForm;
import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.User;
import com.dwalldorf.fuel.repository.CarRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    private final UserService userService;

    private final CarRepository carRepository;

    @Inject
    public CarService(UserService userService, CarRepository carRepository) {
        this.userService = userService;
        this.carRepository = carRepository;
    }

    public Car findById(Long id) {
        return carRepository.findOne(id);
    }

    public List<Car> findByUser(User user) {
        return carRepository.findByUser(user);
    }

    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }

    public Car toModel(CarForm form) {
        Car car = new Car()
                .setId(form.getId())
                .setManufacturer(form.getManufacturer())
                .setModelName(form.getModelName())
                .setYear(form.getYear())
                .setLicensePlate(form.getLicensePlate());

        if (form.getUserId() != null) {
            car.setUser(userService.findById(form.getUserId()));
        }

        return car;
    }

    public CarForm fromModel(Car model) {
        if (model == null) {
            return null;
        }

        return new CarForm()
                .setId(model.getId())
                .setUserId(model.getUser().getId())
                .setManufacturer(model.getManufacturer())
                .setModelName(model.getModelName())
                .setYear(model.getYear())
                .setLicensePlate(model.getLicensePlate());
    }
}