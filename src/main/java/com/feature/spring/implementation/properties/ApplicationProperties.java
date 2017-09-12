package com.feature.spring.implementation.properties;

public class ApplicationProperties {

    private final FeatureFlagProperties featureFlagProperties;

    public ApplicationProperties(final FeatureFlagProperties featureFlagProperties) {
        this.featureFlagProperties = featureFlagProperties;
    }

    public FeatureFlagProperties getFeatureFlagProperties() {
        return featureFlagProperties;
    }
}
