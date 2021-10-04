package com.epam.smyrnov.items.autoconfig;

import lombok.val;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Properties;

import static com.google.common.base.MoreObjects.firstNonNull;

class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) {
        val factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        return new PropertiesPropertySource(
                firstNonNull(name, resource.getResource().getFilename()),
                firstNonNull(factory.getObject(), new Properties()));
    }
}
