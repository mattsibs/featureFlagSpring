package com.feature.spring.implementation.configuration;

import com.feature.spring.implementation.controller.IntegrationStatusController;
import com.feature.spring.implementation.proxy.FeatureFlagMethodInterceptor;
import com.feature.spring.implementation.proxy.FeatureFlagProxy;
import com.feature.spring.implementation.service.ConfigurationService;
import com.feature.spring.implementation.service.ConfigurationServiceFactory;
import com.feature.spring.implementation.service.IntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PropertiesConfiguration.class)
public class SpringServiceBeanConfiguration {

    @Autowired
    private PropertiesConfiguration propertiesConfiguration;

    @Bean
    public ConfigurationService configurationService() {
        return ConfigurationServiceFactory.create(propertiesConfiguration.getApplicationProperties());
    }

    @Bean
    public IntegrationService integrationService() {
        return new IntegrationService();
    }

    @Bean
    public FeatureFlagMethodInterceptor featureFlagMethodInterceptor() {
        return new FeatureFlagMethodInterceptor(configurationService());
    }

    @Bean
    public FeatureFlagProxy featureFlagProxy() {
        return new FeatureFlagProxy(featureFlagMethodInterceptor());
    }

    @Bean
    public IntegrationStatusController integrationStatusController() {
        return new IntegrationStatusController(integrationService());
    }

}
