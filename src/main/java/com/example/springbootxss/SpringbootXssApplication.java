package com.example.springbootxss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class SpringbootXssApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootXssApplication.class, args);
    }
}
