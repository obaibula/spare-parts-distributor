package com.example.sparepartsdistributor.user;

import com.example.sparepartsdistributor.config.TestDatabaseContainerConfig;
import com.example.sparepartsdistributor.user.UserRepository;
import net.minidev.json.JSONObject;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestDatabaseContainerConfig.class)
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
class UserRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private static PostgreSQLContainer<?> container;
    private static String createUserUrl;
    private static JSONObject userJsonObject;
    private static HttpHeaders headers;

    @BeforeEach
    void setUp(@Autowired Flyway flyway) {
        flyway.clean();
        flyway.migrate();
    }

    @BeforeAll
    public static void runBeforeAllTestMethods() {
        createUserUrl = "http://localhost:8080/api/v1/users";

        headers = new HttpHeaders();
        headers.setContentType(APPLICATION_JSON);
        userJsonObject = new JSONObject();
        userJsonObject.put("email", "user@mail.com");
        userJsonObject.put("password", "Aa12345@");
    }

    @Test
    void test() {


    }
}