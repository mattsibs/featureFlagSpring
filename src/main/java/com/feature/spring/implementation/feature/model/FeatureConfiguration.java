package com.feature.spring.implementation.feature.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureConfiguration {

    @JsonProperty
    private List<Feature> features;

    public boolean isActive(final Feature feature) {
        return features.contains(feature);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("features", features)
                .toString();
    }
}
