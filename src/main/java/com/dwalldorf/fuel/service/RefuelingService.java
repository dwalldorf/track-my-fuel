package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.model.Refueling;
import com.dwalldorf.fuel.repository.RefuelingRepository;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class RefuelingService {

    private final RefuelingRepository refuelingRepository;

    @Inject
    public RefuelingService(RefuelingRepository refuelingRepository) {
        this.refuelingRepository = refuelingRepository;
    }

    public List<Refueling> findAllByUser(String userId) {
        return refuelingRepository.findAllByUserId(userId);
    }
}