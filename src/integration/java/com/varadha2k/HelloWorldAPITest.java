package com.varadha2k;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloWorldController.class)
public class HelloWorldAPITest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testHelloWorld() throws Exception
	{
		mockMvc.perform(get("/test"))
		.andExpect(content().string("Hello World!"))
		.andExpect(status().is2xxSuccessful());
	}
	@Test
	public void testHelloWorldName() throws Exception
	{
		mockMvc.perform(get("/test/Ram"))
		.andExpect(content().string("Your Name is: - Ram"))
		.andExpect(status().is2xxSuccessful());
	}
}
