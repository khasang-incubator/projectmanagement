package io.khasang.pm.config;

import io.khasang.pm.controller.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // something
    @Bean
    public Cat cat() {
        return new Cat("Barsik", "Great");
    }

}
