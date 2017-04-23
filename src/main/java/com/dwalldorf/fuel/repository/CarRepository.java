package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Car;
import com.dwalldorf.fuel.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findByUser(User user);
}