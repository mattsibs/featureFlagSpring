package com.feature.spring.implementation.configuration;

import com.feature.spring.implementation.controller.StatusController;
import com.feature.spring.implementation.proxy.FeatureFlagMethodInterceptor;
import com.feature.spring.implementation.proxy.FeatureFlagProxy;
import com.feature.spring.implementation.service.AnalysisAdapterService;
import com.feature.spring.implementation.service.ConfigurationService;
import com.feature.spring.implementation.service.ConfigurationServiceFactory;
import com.feature.spring.implementation.service.IntegrationService;
import com.feature.spring.implementation.service.analysis.AnalysisOffService;
import com.feature.spring.implementation.service.analysis.AnalysisOnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

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
    @Lazy
    public AnalysisOffService analysisOffService() {
        return new AnalysisOffService();
    }

    @Bean
    @Lazy
    public AnalysisOnService analysisOnService() {
        return new AnalysisOnService();
    }

    @Bean
    public AnalysisAdapterService adapterService() {
        return new AnalysisAdapterService(analysisOffService(), analysisOnService(), configurationService());
    }

    @Bean
    public FeatureFlagMethodInterceptor featureFlagMethodInterceptor() {
        return new FeatureFlagMethodInterceptor(configurationService());
    }

    @Bean
    public FeatureFlagProxy featureFlagProxy() {
        return new FeatureFlagProxy(featureFlagMethodInterceptor());
    }


}
