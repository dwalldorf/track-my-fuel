package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Refueling;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RefuelingRepository extends MongoRepository<Refueling, String> {

    List<Refueling> findAllByUserId(String userId);
}