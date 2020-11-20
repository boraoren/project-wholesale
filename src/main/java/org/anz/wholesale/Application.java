package org.anz.wholesale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"org.anz.wholesale.models"})
public class Application {

    public static void main(String[] args) {
        SpringApplication
                .run(Application.class, args);
    }

}

