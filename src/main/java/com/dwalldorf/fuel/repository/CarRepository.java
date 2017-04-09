package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Car;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, String> {

    List<Car> findByUserId(String userId);
}