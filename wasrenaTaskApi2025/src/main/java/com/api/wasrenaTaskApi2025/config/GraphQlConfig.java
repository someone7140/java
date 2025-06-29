package com.api.wasrenaTaskApi2025.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder ->
                wiringBuilder
                        .scalar(ExtendedScalars.Json)
                        .scalar(ExtendedScalars.DateTime);
    }
}
