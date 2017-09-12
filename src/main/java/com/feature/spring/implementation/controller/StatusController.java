package com.feature.spring.implementation.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.feature.spring.implementation.service.AnalysisAdapterService;
import com.feature.spring.implementation.service.IntegrationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    private final IntegrationService integrationService;
    private final AnalysisAdapterService analysisAdapterService;

    public StatusController(final IntegrationService integrationService,
                            final AnalysisAdapterService analysisAdapterService) {
        this.integrationService = integrationService;
        this.analysisAdapterService = analysisAdapterService;
    }

    @RequestMapping("/")
    @ResponseBody
    public Status status() {
        return new Status(
                integrationService.status(),
                analysisAdapterService.getAnalysisStatus()
        );
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Status {
        @JsonProperty
        private String integrationStatus;
        @JsonProperty
        private String analysisStatus;

        public Status(final String integrationStatus, final String analysisStatus) {
            this.integrationStatus = integrationStatus;
            this.analysisStatus = analysisStatus;
        }
    }
}
