package com.varadha2k;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/test")
	public String HelloWorld() {
		return "Hello World!";
	}

	@GetMapping("/test/{name}")
	public String HelloWorld(@PathVariable final String name) {
		return "Your Name is: - "  + name;
	}
}
