package com.livecricketscores.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Paths;

@Configuration
public class CassandraDBConfig {

    @Value("${datastax.astra.secure-connect-bundle}")
    private String secure_connect_bundle;

    @Bean
    public CqlSessionBuilderCustomizer sessionBuilderCustomizer() {
        return builder -> builder.withCloudSecureConnectBundle(Paths.get(secure_connect_bundle));
    }

}
