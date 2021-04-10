package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.var;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
classes = DemoApplication.class)
class DemoApplicationTests {

	@Autowired
    PetRepository respository;

	@Test
	public void contextLoads() {
		var pet = new Pet();
		pet.setName("banana");
		pet.setTag("ASDF-234");

		assertNull(pet.getId());

		this.respository.save(pet);

		assertNotNull(pet.getId());
	}

}
