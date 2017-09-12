package com.feature.spring.implementation.service;

import com.feature.spring.implementation.feature.model.Feature;
import com.feature.spring.implementation.service.analysis.AnalysisOffService;
import com.feature.spring.implementation.service.analysis.AnalysisOnService;

public class AnalysisAdapterService {

    private final AnalysisOffService analysisOffService;
    private final AnalysisOnService analysisOnService;
    private final ConfigurationService configurationService;

    public AnalysisAdapterService(final AnalysisOffService analysisOffService,
                                  final AnalysisOnService analysisOnService,
                                  final ConfigurationService configurationService) {
        this.analysisOffService = analysisOffService;
        this.analysisOnService = analysisOnService;
        this.configurationService = configurationService;
    }

    public String getAnalysisStatus() {
        if (configurationService
                .getFeatureConfiguration()
                .isActive(Feature.ANALYSIS)) {
            return analysisOnService.result();
        }

        return analysisOffService.result();
    }
}
