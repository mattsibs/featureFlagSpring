package com.feature.spring.implementation.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.net.URL;

public class HttpConfigurationLoader<T> implements ConfigurationLoader<T> {

    private final URL pathToConfig;
    private final Class<T> type;

    public HttpConfigurationLoader(final URL pathToConfig, final Class<T> type) {
        this.pathToConfig = pathToConfig;
        this.type = type;
    }

    public T load() {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(pathToConfig, type);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
