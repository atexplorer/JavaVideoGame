package com.ethan.RPG.controller;

import com.ethan.RPG.dto.request.DiceRequest;
import com.ethan.RPG.service.CombatServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.mockito.Mockito.when;

@WebMvcTest(CombatController.class)
public class CombatControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CombatServiceImpl combatService;

    @Test
    public void getToSucceedResultTest() throws Exception {
        DiceRequest diceRequest = new DiceRequest(0);

        //ObjectMapper is used to set up our request body
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter writer = objectMapper.writer().withDefaultPrettyPrinter();
        String requestJson = writer.writeValueAsString(diceRequest);

        //ArgumentMatchers allows you to note that any argument can be passed into a method to get a response
        when(combatService.getToSucceedResult(ArgumentMatchers.any())).thenReturn(20);

        mockMvc.perform(post("/combat/toSucceed")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.toSucceedResult").value(20));

    }
}
