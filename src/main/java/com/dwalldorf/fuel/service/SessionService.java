package com.dwalldorf.fuel.service;

import com.dwalldorf.fuel.config.SecurityUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }
}