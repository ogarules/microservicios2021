package com.example.demo.services;

import java.net.URI;

import com.example.demo.models.BotRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class BotServiceImpl implements BotService {

    @Autowired
    RestTemplate template;

    @CircuitBreaker(name = "botService", fallbackMethod = "fallBackBotReturn")
    @Override
    public String getBotResponse(String request) {
        URI uri = URI.create("https://tecdemoaxity.azurewebsites.net/api/directmessage");

        BotRequest botRequest = new BotRequest();
        botRequest.setValue(request);
        botRequest.setGenerateAudio(true);

        BotRequest[] response = this.template.postForObject(uri, botRequest, BotRequest[].class);

        return response[0].getValue();
    }
    
    public String fallBackBotReturn(Exception e){
        return "Hola ando un poco borracho ahora, intenta mas tarde";
    }
}
