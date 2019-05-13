package io.khasang.pm.service.impl;

import io.khasang.pm.service.KnightService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class KnightServiceImpl implements KnightService {

    @Override
    public String getAchievement(String value) {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (value.equalsIgnoreCase("dragon")) {
            throw new IllegalArgumentException("Dragon has eaten the knight!");
        }

        return "I am slave a " + value + "!";
    }
}
