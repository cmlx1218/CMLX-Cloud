package com.cmlx.auth.properties;


import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.IOException;
import java.util.List;

/**
 * @Author CMLX
 * @Date -> 2021/10/11 16:28
 * @Desc ->
 **/
public class YamlPropertySourceFactory implements PropertySourceFactory {
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
        List<PropertySource<?>> load = loader.load(name, resource.getResource());
        if (load!=null && !load.isEmpty()) {
            return load.get(0);
        }
        return null;
    }
}