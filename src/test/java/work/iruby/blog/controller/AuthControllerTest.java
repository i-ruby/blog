package work.iruby.blog.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import work.iruby.blog.entity.BaseMsg;
import work.iruby.blog.entity.BlogUser;
import work.iruby.blog.entity.LoginMsg;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url;

    @BeforeEach
    void init() {
        url = String.format("http://localhost:%d/", port);
    }

    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class login_then_auth {
        @Test
        @Order(1)
        void before_login_auth() {
            Assertions.assertFalse(restTemplate.getForObject(url + "/auth", LoginMsg.class).getLogin());
        }

        @Test
        @Order(2)
        void after_login_auth() {
            Map<String, String> map = new HashMap<>();
            map.put("username", "iruby");
            map.put("password", "234");
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(map, headers);

            ResponseEntity<BaseMsg<BlogUser>> response = restTemplate.exchange(url + "/auth/login", HttpMethod.POST, entity, new ParameterizedTypeReference<BaseMsg<BlogUser>>() {
            });
            Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().getUsername(), "iruby");

            String SESSION = Objects.requireNonNull(response.getHeaders().get("Set-Cookie")).get(0).split(";")[0];
            String XSRFTOKEN = Objects.requireNonNull(response.getHeaders().get("Set-Cookie")).get(0).split(";")[1];
            headers.add("Cookie", SESSION + ";" + XSRFTOKEN);
            entity = new HttpEntity<>(null, headers);
            response = restTemplate.exchange(url + "/auth", HttpMethod.GET, entity, new ParameterizedTypeReference<BaseMsg<BlogUser>>() {
            });
            Assertions.assertEquals(Objects.requireNonNull(response.getBody()).getData().getUsername(), "iruby");

        }

    }

}