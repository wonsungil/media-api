package com.bbp.bbptest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.bbp.bbptest"})
public class BbpTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BbpTestApplication.class, args);
    }

}
