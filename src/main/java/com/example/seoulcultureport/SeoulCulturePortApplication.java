package com.example.seoulcultureport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SeoulCulturePortApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeoulCulturePortApplication.class, args);
    }

}
