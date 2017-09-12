package com.feature.spring.implementation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class Main {

    public static void main(final String[] args) {
        SpringApplication.run(Main.class, args);
    }

}
