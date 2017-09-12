package com.feature.spring.implementation.controller;

import com.feature.spring.implementation.service.IntegrationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IntegrationStatusController {

    private final IntegrationService integrationService;

    public IntegrationStatusController(final IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @RequestMapping("/")
    @ResponseBody
    public String status() {
        return integrationService.isThisActive();
    }
}
