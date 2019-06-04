package io.khasang.pm.config;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.DocumentDao;
import io.khasang.pm.dao.impl.CatDaoImpl;
import io.khasang.pm.dao.impl.DocumentDaoImpl;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Document;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public CatDao catDao() {
        return new CatDaoImpl(Cat.class);
    }

    @Bean
    public DocumentDao documentDao(){
        return new DocumentDaoImpl(Document.class);
    }

}
