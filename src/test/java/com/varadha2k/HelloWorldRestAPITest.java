package com.varadha2k;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
/*
 * this test class uses RestAssured to test the rest API
 * REST Assured provides a DSL implemention for testing a REST API
 * 
 * 
 * Given a Precondition
 * When a action is performed
 * Then this is the expected outcome
 */

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldRestAPITest {
	
	@LocalServerPort
	private int port;
	
	@Test
	public void testHelloWorld() {
		when()
		.get(String.format("http://localhost:%s/test", port))
		.then()
		.statusCode(is(200))
		.body(containsString("Hello World!"));
	}
	
	@Test
	public void testHelloWorldWithName() {
		when()
		.get(String.format("http://localhost:%s/test/Ram", port))
		.then()
		.statusCode(is(200))
		.body(containsString("Your Name is: - Ram"));
	}
	

	
}
