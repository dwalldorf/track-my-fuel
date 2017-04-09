package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.repository.CarRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Inject
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car findById(String id) {
        return carRepository.findOne(id);
    }

    public List<Car> findByUserId(String userId) {
        return carRepository.findByUserId(userId);
    }

    @Transactional
    public void save(Car car) {
        carRepository.save(car);
    }
}