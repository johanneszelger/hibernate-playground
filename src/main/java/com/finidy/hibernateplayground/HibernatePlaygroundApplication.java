package com.finidy.hibernateplayground;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class HibernatePlaygroundApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernatePlaygroundApplication.class, args);
    }

}
