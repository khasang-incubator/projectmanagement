package io.khasang.pm.config;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.DriverDao;
import io.khasang.pm.dao.impl.CatDaoImpl;
import io.khasang.pm.dao.impl.DriverDaoImpl;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public CatDao catDao(){
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public DriverDao driverDao(){
        return new DriverDaoImpl(Driver.class);
    }
}
