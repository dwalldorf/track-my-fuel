package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.repository.CarRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Inject
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}