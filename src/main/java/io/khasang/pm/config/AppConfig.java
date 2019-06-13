package io.khasang.pm.config;

import io.khasang.pm.dao.CatDao;
import io.khasang.pm.dao.DocumentDao;
import io.khasang.pm.dao.EmployeeDao;
import io.khasang.pm.dao.ProjectDao;
import io.khasang.pm.dao.RoleDao;
import io.khasang.pm.dao.impl.CatDaoImpl;
import io.khasang.pm.dao.impl.DocumentDaoImpl;
import io.khasang.pm.dao.impl.EmployeeDaoImpl;
import io.khasang.pm.dao.impl.ProjectDaoImpl;
import io.khasang.pm.dao.impl.RoleDaoImpl;
import io.khasang.pm.entity.Cat;
import io.khasang.pm.entity.Employee;
import io.khasang.pm.entity.Project;
import io.khasang.pm.entity.Document;
import io.khasang.pm.entity.Role;
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
    public RoleDao roleDao() {
        return new RoleDaoImpl(Role.class);
    }

    @Bean
    public EmployeeDao employeeDao(){
        return new EmployeeDaoImpl(Employee.class);
    }

}