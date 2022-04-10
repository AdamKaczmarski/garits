package com.garits;

import com.garits.customer.Customer;
import com.garits.part.Part;
import com.garits.user.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class GaritsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GaritsApplication.class, args);
        User user = new User();
        Part p1 = new Part();
        Part p2 = new Part();
        Part p3 = new Part();
        Customer c = new Customer();
        /*
        * Use those objects to create PDF report
        * I will pull the data from the database later
        * */
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/*").allowedOrigins("*");
            }
        };
    }
}
