package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.repository.RefuelingRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class RefuelingService {

    private final RefuelingRepository refuelingRepository;

    @Inject
    public RefuelingService(RefuelingRepository refuelingRepository) {
        this.refuelingRepository = refuelingRepository;
    }
}