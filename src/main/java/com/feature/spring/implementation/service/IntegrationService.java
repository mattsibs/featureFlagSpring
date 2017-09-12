package com.feature.spring.implementation.service;


import com.feature.spring.implementation.annotation.FeatureFlag;
import com.feature.spring.implementation.feature.model.Feature;

@FeatureFlag(flag = Feature.INTEGRATION)
public class IntegrationService {

    public String status() {
        return "Integration service active";
    }

}
