package com.epam.smyrnov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ItemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemsApplication.class, args);
    }
}
