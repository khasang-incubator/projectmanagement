package io.khasang.pm.config;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.impl.CatDaoImpl;
import io.khasang.pm.entity.Cat;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    public CatDao catDao(){
        return new CatDaoImpl(Cat.class);
    }

}
