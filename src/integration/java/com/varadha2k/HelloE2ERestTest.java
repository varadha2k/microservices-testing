package com.varadha2k;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloE2ERestTest {

    @LocalServerPort
    private int port;

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldReturnHelloWorld() throws Exception {
        when()
                .get(String.format("http://localhost:%s/hello", port))
                .then()
                .statusCode(is(200))
                .body(containsString("Hello World!"));
    }
}
