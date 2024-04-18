package com.finidy.hibernateplayground;

import io.hypersistence.utils.jdbc.validator.SQLStatementCountValidator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;


@EnableAutoConfiguration
public class AbstractTest {

    @BeforeEach
    void setUp() {
        SQLStatementCountValidator.reset();
        System.out.println("************************************************");
        System.out.println("\n\n\n\n\n");

    }

    @AfterEach
    void tearDown() {
        System.out.println("\n\n\n\n\n");
        System.out.println("************************************************");
        SQLStatementCountValidator.reset();
    }
}