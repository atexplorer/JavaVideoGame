package com.ethan.RPG.exceptions;

import com.ethan.RPG.controller.MonsterController;
import com.ethan.RPG.entity.Monster;
import com.ethan.RPG.service.MonsterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MonsterController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MonsterService monsterService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testHandleMonsterNotFoundException() throws Exception {
        Monster monster = new Monster(1L, "Igor", "Helpful assistant to Dr. Frankenstein", "Human");
        String jsonRequest = objectMapper.writeValueAsString(monster);

        when(monsterService.updateMonster(any(), eq(1L)))
                .thenThrow(new MonsterNotFoundException("No monster found with that id"));

        mockMvc.perform(put("/monsters/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.statusCode").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("No monster found with that id"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
}
