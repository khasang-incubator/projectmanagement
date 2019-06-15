package io.khasang.pm.config;

import io.khasang.pm.dao.*;
import io.khasang.pm.dao.impl.*;
import io.khasang.pm.entity.*;
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
    public ProjectDao projectDao() {return new ProjectDaoImpl(Project.class);
    }

    @Bean
    public DocumentDao documentDao(){
        return new DocumentDaoImpl(Document.class);
    }

    @Bean
    public EmployeeDao employeeDao(){
        return new EmployeeDaoImpl(Employee.class);
    }

    @Bean
    public ChildDocumentDao childDocumentDao() {
        return new ChildDocumentDaoImpl(ChildDocument.class);
    }

    /*@Bean
    public BarsukDao barsukDao() {
        return new BarsukDaoImpl(Barsuk.class);
    }*/
}
