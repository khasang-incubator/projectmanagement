package io.khasang.pm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;

// Этот класс содержит в себе инфу по подкл. к БД
@Configuration
@PropertySource(value = "classpath:util.properties")
@PropertySource(value = "classpath:auth.properties")
public class DataConfig {
    private Environment environment;

    /* datasource - содержит все основные настройки, чтобы подкл. к БД:
     содержит в себе драйвер (для подкл. к БД),
     содержит URL к нашей БД, user и пароль*/
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.postgresql.driver"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.postgresql.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.postgresql.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.postgresql.password"));
        return dataSource;
    }

    /*jdbctemplate - класс, позволяющий осуществлять какие-либо операции с БД (делаем SQL query)
     */
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());//C какими настройками нашему jdbcTemplate идти в нашу БД
        return jdbcTemplate;
    }

    //Учебная версия, пароли и юзеры нельзя хардКодить
    @Bean
    public UserDetailsService userDetailsService(){
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("user").password("user").roles("USER").build());
        manager.createUser(users.username("admin").password("admin").roles("ADMIN").build());
        return manager;
    }

    /*@Bean
    public UserDetailsService userDetailsService(){
        JdbcDaoImpl dao = new JdbcDaoImpl();
        dao.setDataSource(dataSource());
        dao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery")); //Идем в БД и чекаем есть ли там такие юзеры с таким паролем
        dao.setAuthoritiesByUsernameQuery("rolesByQuery"); // Чекаем есть ли у нас такая роль и смотрит - можно туда ходить или нет
        return dao;
    }*/

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
