package com.garits;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.garits.customer.Customer;
import com.garits.part.Part;
import com.garits.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Authors: Adam Kaczmarski, Adel Zaky
 * Date of submission: 14/04/2022
 * Github: https://github.com/AdamKaczmarski/garits
 * Adel has been creating Entity Classes and simple API endpoints (get all, get one, post, delete) for 3-4 objects.
 * He also tested and created PDF templates for the reports. Adam has connected the reports with the database and created a controller for them.
 * Adam has taken care of the database implementation, fixing, upgrades; The Backend logic and all other classes and methods that were not mentioned before.
 * Adam has also created the frontend and the frontend logic which sends API requests to the backend.
 */
@SpringBootApplication
@EnableScheduling
public class GaritsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaritsApplication.class, args);
    }
/*    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }*/
    @Bean
    ObjectMapper myObjectMapper() {
        Hibernate5Module m = new Hibernate5Module();
        m.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(m);
        return mapper;
    }}
