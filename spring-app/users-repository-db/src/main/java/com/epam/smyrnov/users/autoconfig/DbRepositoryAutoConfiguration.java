package com.epam.smyrnov.users.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:db-properties.yml", factory = YamlPropertySourceFactory.class)
@ComponentScan("com.epam.smyrnov.users")
public class DbRepositoryAutoConfiguration {

}
