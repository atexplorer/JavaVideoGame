package com.ethan.RPG.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class ServiceConfig {

    @Bean
    public Random randomConfig(){
        return new Random();
    }
}
