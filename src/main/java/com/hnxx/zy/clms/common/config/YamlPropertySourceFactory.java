package com.hnxx.zy.clms.common.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author 南街北巷
 * @data 2020/4/11 14:53
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {
    /**
     * Create a {@link PropertySource} that wraps the given resource.
     *
     * @param name     the name of the property source
     * @param resource the resource (potentially encoded) to wrap
     * @return the new {@link PropertySource} (never {@code null})
     * @throws IOException if resource resolution failed
     */
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource)
            throws IOException {
        Properties properties = load(resource);
        return new PropertiesPropertySource(name != null ? name :
                Objects.requireNonNull(resource.getResource().getFilename(), "Some error message"),
                properties);
    }

    /**
     * Load properties from the YAML file.
     *
     * @param resource Instance of {@link EncodedResource}
     * @return instance of properties
     */
    private Properties load(EncodedResource resource) throws FileNotFoundException {
        try {
            YamlPropertiesFactoryBean githubFactory = new YamlPropertiesFactoryBean();
            githubFactory.setResources(resource.getResource());
            githubFactory.afterPropertiesSet();

            return githubFactory.getObject();
        } catch (IllegalStateException ex) {
            /*
             * Ignore resource not found.
             */
            Throwable cause = ex.getCause();
            if(cause instanceof FileNotFoundException) {
                throw (FileNotFoundException) cause;
            }
            throw ex;
        }
    }
}
