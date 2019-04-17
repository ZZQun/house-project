package com.house.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;


@Configuration

public class DataSourceConfig {
    @Value("${spring.datasource.druid.url}")
    String url;

    @Value("${spring.datasource.druid.username}")
    String username;

    @Value("${spring.datasource.druid.password}")
    String password;

    @Value("${spring.datasource.druid.driver-class-name}")
    String driverClassName;

    @Bean
    public DataSource dataSource(StandardEnvironment env) {
        Properties properties = new Properties();
        DruidDataSource druidDataSource = new DruidDataSource();
        PropertySource<?> appProperties = env.getPropertySources().get("applicationConfig: [classpath:/application.yml]");


        Map<String, Object> source = (Map<String, Object>) appProperties.getSource();
        properties.putAll(source);
        druidDataSource.configFromPropety(properties);
        druidDataSource.setUrl(url);
        druidDataSource.setPassword(password);
        druidDataSource.setUsername(username);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }
}
