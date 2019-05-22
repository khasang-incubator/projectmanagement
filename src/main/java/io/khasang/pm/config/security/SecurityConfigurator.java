package io.khasang.pm.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@PropertySource(value = "classpath:auth.properties")
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    @Autowired
    public void setUserDetailsService(DataSource dataSource, Environment environment){
        JdbcDaoImpl dao = new JdbcDaoImpl();
        dao.setDataSource(dataSource);
        dao.setUsersByUsernameQuery(environment.getRequiredProperty("usersByQuery"));
        dao.setAuthoritiesByUsernameQuery(environment.getRequiredProperty("rolesByQuery"));
        this.userDetailsService = dao;
    }

    @Override
    public void configure(HttpSecurity http) throws  Exception{
            http.authorizeRequests().antMatchers("/admin/**").access("hasRole('ADMIN')")
                    .antMatchers("/user/**").access("hasRole('USER')")
                    .antMatchers("/").permitAll()
                    .and().csrf().disable().cors().disable().formLogin().defaultSuccessUrl("/",false);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
