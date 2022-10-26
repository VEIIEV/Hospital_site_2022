package com.example.Hospital_site_2022.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

// указываем, что это конфигурация, которая опирается на такой-то пакет и такой-то config-файл, плюс позволяет управлять транзакциями
@Configuration
@EnableJpaRepositories
@PropertySource("application.properties")
@EnableTransactionManagement
public class MyJpaConfig {

    // сервис текущего окружения (с параметрами и т.д.)
    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        // настраиваем, откуда мы будем брать данные: адрес/логин/пароль/драйвер
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name.driverClassName"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));

        return dataSource;
    }

    // дальше можно сконфигурировать entityManagerFactory, transactionManager, прочите дополнительные свойства Hibernate
}