package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import lombok.var;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
classes = DemoApplication.class)
class DemoApplicationTests {

	@Autowired
    PetRepository respository;

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper objectmapper;

	@Test
	public void contextLoads() {
		var pet = new Pet();
		pet.setName("banana");
		pet.setTag("ASDF-234");

		assertNull(pet.getId());

		this.respository.save(pet);

		assertNotNull(pet.getId());
	}

	@Test
	public void testMvc() throws Exception{
		Pet pet = new Pet();
		pet.setName("banana");
		
		String body = objectmapper.writeValueAsString(pet);

		String result = mvc.perform(post("/pets").contentType("application/json").content(body))
		            .andExpect(status().isOk())
					.andExpect(content().contentTypeCompatibleWith("application/json"))
					.andDo(print())
					.andExpect(jsonPath("$.name", is("banana")))
					.andReturn().getResponse().getContentAsString();

	}

	@Test
	public void testValidations() throws Exception{
		Pet pet = new Pet();
		pet.setAge(100);
		
		String body = objectmapper.writeValueAsString(pet);

		mvc.perform(post("/pets").contentType("application/json").content(body))
		            .andExpect(status().isBadRequest());

	}

	@Test
	public void testValidationsUpdate() throws Exception{
		Pet pet = new Pet();
		pet.setId(1);
		pet.setAge(200);
		
		String body = objectmapper.writeValueAsString(pet);

		mvc.perform(put("/pets/1").contentType("application/json").content(body))
		            .andExpect(status().isBadRequest());

	}

}
