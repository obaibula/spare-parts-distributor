package com.example.sparepartsdistributor.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Test configuration class for configuring the test database.
 * This class provides the necessary beans and methods to set up a PostgreSQL container
 * and configure the data source properties for testing purposes.
 */
@TestConfiguration(proxyBeanMethods = false)
public class TestDatabaseContainerConfig {

    /**
     * Creates and configures a PostgreSQL container as a bean.
     * The container is initialized with the specified PostgreSQL version.
     * The data source properties are dynamically set in the provided {@link DynamicPropertyRegistry}.
     *
     * @param registry the dynamic property registry to add the data source properties
     * @return a {@link PostgreSQLContainer} instance configured with the appropriate PostgreSQL version
     */
    @Bean
    @ServiceConnection
    @DynamicPropertySource
    PostgreSQLContainer<?> postgresContainer(DynamicPropertyRegistry registry) {
        PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14.8");
        addAppropriateDataSources(registry, container);
        return container;
    }

    private static void addAppropriateDataSources(DynamicPropertyRegistry registry, PostgreSQLContainer<?> container) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }
}
