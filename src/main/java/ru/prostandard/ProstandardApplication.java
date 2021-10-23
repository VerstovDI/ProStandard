package ru.prostandard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProstandardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProstandardApplication.class, args);
    }

}
