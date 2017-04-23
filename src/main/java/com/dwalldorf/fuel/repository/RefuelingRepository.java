package com.dwalldorf.fuel.repository;

import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.model.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface RefuelingRepository extends CrudRepository<Refueling, Long> {

    List<Refueling> findAllByUser(User user);
}