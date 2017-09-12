package com.feature.spring.implementation.properties;

import java.io.File;
import java.net.URL;

public class FeatureFlagProperties {
    private final FeatureFlagPreference featureFlagPreference;
    private final File featureFile;
    private final URL featureFileUrl;

    private FeatureFlagProperties(final FeatureFlagPreference featureFlagPreference,
                                  final File featureFile,
                                  final URL featureFileUrl) {
        this.featureFlagPreference = featureFlagPreference;
        this.featureFile = featureFile;
        this.featureFileUrl = featureFileUrl;
    }

    public static FeatureFlagProperties file(final String featureFilePath) {
        return new FeatureFlagProperties(FeatureFlagPreference.FILE, new File(featureFilePath), null);
    }

    public static FeatureFlagProperties http(final URL featureFileUrl) {
        return new FeatureFlagProperties(FeatureFlagPreference.HTTP, null, featureFileUrl);
    }

    public FeatureFlagPreference getFeatureFlagPreference() {
        return featureFlagPreference;
    }

    public File getFeatureFile() {
        return featureFile;
    }

    public URL getFeatureFileUrl() {
        return featureFileUrl;
    }
}
