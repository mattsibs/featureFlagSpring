package com.feature.spring.implementation.proxy;

import com.feature.spring.implementation.annotation.FeatureFlag;
import org.apache.log4j.Logger;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;

public class FeatureFlagProxy extends AbstractAutoProxyCreator {
    private static final Logger L = Logger.getLogger(FeatureFlagProxy.class);

    private final FeatureFlagMethodInterceptor inactiveFeatureFlagMethodInterceptor;

    public FeatureFlagProxy(final FeatureFlagMethodInterceptor inactiveFeatureFlagMethodInterceptor) {
        this.inactiveFeatureFlagMethodInterceptor = inactiveFeatureFlagMethodInterceptor;
    }

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(final Class<?> beanClass,
                                                    final String beanName,
                                                    final TargetSource customTargetSource) throws BeansException {

        if (beanClass.isAnnotationPresent(FeatureFlag.class)) {
            L.info("Adding feature flag proxy for class " + beanClass);
            return new Object[]{inactiveFeatureFlagMethodInterceptor};
        }

        return DO_NOT_PROXY;
    }

}
