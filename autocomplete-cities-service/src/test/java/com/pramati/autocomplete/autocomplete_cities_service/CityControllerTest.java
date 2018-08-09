package com.pramati.autocomplete.autocomplete_cities_service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@RunWith(value = SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes={App.class})
public class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetDashboardApplications() throws Exception {
		String response = this.mockMvc.perform(get("/suggest_cities?start=De&atmost=1")).andDo(print())
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		assertNotNull(response);
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJSON = objectMapper.readTree(response);
		String status = responseJSON.get("status").asText();
		assertEquals("OK", status);
		JsonNode payload = responseJSON.get("result");
		assertNotNull(payload);
	}
}
