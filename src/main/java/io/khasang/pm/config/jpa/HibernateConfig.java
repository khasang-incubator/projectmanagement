package io.khasang.pm.config.jpa;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = "classpath:hibernate.properties")
public class HibernateConfig {

    private DataSource dataSource;
    private Environment environment;

    public HibernateConfig(DataSource dataSource, Environment environment){
        this.dataSource = dataSource;
        this.environment = environment;
    }

    @Bean
//    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(this.dataSource);
        sessionFactory.setPackagesToScan("io.khasang.pm.entity");
        sessionFactory.setHibernateProperties(properties());
        return  sessionFactory;
    }

    private Properties properties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect",this.environment.getProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql",this.environment.getProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql",this.environment.getProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto",this.environment.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("show_sql",this.environment.getProperty("hibernate.format_sql"));
        return  properties;
    }
}
