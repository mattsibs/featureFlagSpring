package com.feature.spring.implementation.configuration;

import com.feature.spring.implementation.properties.ApplicationProperties;
import com.feature.spring.implementation.properties.FeatureFlagPreference;
import com.feature.spring.implementation.properties.FeatureFlagProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.MalformedURLException;
import java.net.URL;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfiguration {

    @Value("${configuration.preference}")
    private FeatureFlagPreference featureFlagPreference;

    @Value("${configuration.http.url}")
    private String featureFlagFileUrl;

    @Value("${configuration.file.path}")
    private String featureFlagFilePath;

    public ApplicationProperties getApplicationProperties() {
        try {
            return new ApplicationProperties(getFeatureFlagProperties());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error obtaining application properties", e);
        }
    }

    private FeatureFlagProperties getFeatureFlagProperties() throws MalformedURLException {
        if (FeatureFlagPreference.HTTP.equals(featureFlagPreference)) {
            return FeatureFlagProperties.http(new URL(featureFlagFileUrl));
        }

        return FeatureFlagProperties.file(featureFlagFilePath);
    }
}
