package com.dwalldorf.fuel.controller;

import com.dwalldorf.fuel.config.ApplicationProperties;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class VersionController {

    private static final String BASE_URI = "/version";

    private final ApplicationProperties applicationProperties;

    @Inject
    public VersionController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping(VersionController.BASE_URI)
    public String getVersion() {
        return applicationProperties.getVersion();
    }
}