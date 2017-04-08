package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Refueling;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefuelingRepository extends MongoRepository<Refueling, String> {
}