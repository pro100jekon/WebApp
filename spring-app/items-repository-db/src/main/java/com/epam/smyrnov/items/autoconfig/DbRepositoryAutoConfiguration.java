package com.epam.smyrnov.items.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:db-properties.yml", factory = YamlPropertySourceFactory.class)
@ComponentScan("com.epam.smyrnov.items")
public class DbRepositoryAutoConfiguration {

}
