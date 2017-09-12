package com.feature.spring.implementation.feature;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;

public class FileConfigurationLoader<T> implements ConfigurationLoader<T> {

    private final File file;
    private final Class<T> type;

    public FileConfigurationLoader(final File file, final Class<T> type) {
        this.file = file;
        this.type = type;
    }

    public T load() {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(file, type);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}