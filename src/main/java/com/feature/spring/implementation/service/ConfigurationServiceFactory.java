package com.feature.spring.implementation.service;

import com.feature.spring.implementation.feature.FileConfigurationLoader;
import com.feature.spring.implementation.feature.HttpConfigurationLoader;
import com.feature.spring.implementation.feature.model.FeatureConfiguration;
import com.feature.spring.implementation.properties.ApplicationProperties;
import com.feature.spring.implementation.properties.FeatureFlagPreference;
import com.feature.spring.implementation.properties.FeatureFlagProperties;
import com.google.common.base.Suppliers;

import java.util.concurrent.TimeUnit;

public class ConfigurationServiceFactory {

    public static ConfigurationService create(final ApplicationProperties applicationProperties) {
        FeatureFlagProperties featureFlagProperties = applicationProperties
                .getFeatureFlagProperties();

        if (featureFlagProperties
                .getFeatureFlagPreference()
                .equals(FeatureFlagPreference.FILE)) {

            FileConfigurationLoader<FeatureConfiguration> loader = new FileConfigurationLoader<>(
                    featureFlagProperties.getFeatureFile(),
                    FeatureConfiguration.class
            );

            return new ConfigurationService(loader::load);
        }

        HttpConfigurationLoader<FeatureConfiguration> loader = new HttpConfigurationLoader<>(
                featureFlagProperties.getFeatureFileUrl(),
                FeatureConfiguration.class
        );

        return new ConfigurationService(Suppliers.memoizeWithExpiration(loader::load, 5, TimeUnit.SECONDS));
    }

}
