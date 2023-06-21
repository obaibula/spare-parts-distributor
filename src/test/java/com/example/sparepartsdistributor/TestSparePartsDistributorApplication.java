package com.example.sparepartsdistributor;

import org.junit.Ignore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestSparePartsDistributorApplication {

	@Bean
	@ServiceConnection
	PostgreSQLContainer<?> postgresContainer() {
		return new PostgreSQLContainer<>("postgres:14.8");
	}

	public static void main(String[] args) {
		SpringApplication.from(SparePartsDistributorApplication::main).with(TestSparePartsDistributorApplication.class).run(args);
	}

}
