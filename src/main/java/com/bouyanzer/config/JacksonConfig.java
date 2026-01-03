package com.bouyanzer.config;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module; // Attention: Hibernate6Module pour Spring Boot 3
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public Hibernate6Module hibernateModule() {
        // Ce module permet à Jackson de voir un champ Lazy non chargé et de mettre "null" 
        // au lieu de lancer une exception 500.
        return new Hibernate6Module();
    }
}
