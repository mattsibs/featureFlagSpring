package com.feature.spring.implementation.service;

import com.feature.spring.implementation.feature.model.FeatureConfiguration;

import java.util.function.Supplier;

public class ConfigurationService {

    private final Supplier<FeatureConfiguration> configurationSupplier;

    public ConfigurationService(final Supplier<FeatureConfiguration> configurationSupplier) {
        this.configurationSupplier = configurationSupplier;
    }

    public FeatureConfiguration getFeatureConfiguration() {
        return configurationSupplier.get();
    }


}
