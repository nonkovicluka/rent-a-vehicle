package com.rentavehicle.web.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentavehicle.web.dto.UserDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MessageController {

    @MessageMapping("/register")
    @SendTo("/user/approval")
    public UserDTO registeredUser(@RequestParam String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        UserDTO user;

        user = mapper.readValue(userJson, UserDTO.class);

        return user;
    }
}
