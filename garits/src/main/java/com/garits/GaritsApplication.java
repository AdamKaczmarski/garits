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
