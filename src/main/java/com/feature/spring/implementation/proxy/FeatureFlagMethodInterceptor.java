package com.feature.spring.implementation.proxy;

import com.feature.spring.implementation.annotation.FeatureFlag;
import com.feature.spring.implementation.feature.model.Feature;
import com.feature.spring.implementation.feature.model.FeatureConfiguration;
import com.feature.spring.implementation.service.ConfigurationService;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

public class FeatureFlagMethodInterceptor implements MethodInterceptor {
    private static final Logger L = Logger.getLogger(FeatureFlagMethodInterceptor.class);

    private final ConfigurationService configurationService;

    public FeatureFlagMethodInterceptor(final ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        Class<?> declaringClass = invocation.getMethod().getDeclaringClass();
        Feature featureFlag = declaringClass
                .getDeclaredAnnotation(FeatureFlag.class)
                .flag();

        FeatureConfiguration featureConfiguration = configurationService.getFeatureConfiguration();
        L.info("Debugging feature config " + featureConfiguration);

        boolean featureFlagActive = featureConfiguration
                .isActive(featureFlag);

        if (featureFlagActive) {
            return invocation.proceed();
        }

        throw new UnsupportedOperationException(
                String.format("Feature %s is inactive, class %s is not in use", featureFlag, declaringClass)
        );
    }

}
