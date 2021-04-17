package com.example.demo;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import com.example.demo.controllers.PetController;
import com.example.demo.models.Pet;
import com.example.demo.repositories.PetRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
classes = DemoApplication.class)
public class MockitoTests {
    
    @MockBean
    PetRepository repository;

    @InjectMocks
    @Autowired
    PetController controller;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testMockRepository() throws UnsupportedEncodingException, Exception{
        Pet myPet = new Pet();
        myPet.setAge(3);        
        myPet.setName("Banana");

        String body = objectMapper.writeValueAsString(myPet);
        
        given(repository.save(any())).willReturn(myPet);

        String result = mvc.perform(post("/pets").contentType("application/json").content(body))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith("application/json"))
        .andDo(print())
        .andExpect(jsonPath("$.name", is("Banana")))
        .andReturn().getResponse().getContentAsString();
        
        Mockito.verify(repository).save(any());
    }
}
