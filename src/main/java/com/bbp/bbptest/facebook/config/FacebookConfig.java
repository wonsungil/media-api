package com.bbp.bbptest.facebook.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.facebook.ads.sdk.APIContext;

@Configuration
public class FacebookConfig {
    @Bean(name = "facebookApiContext")
    public APIContext facebookApiContext() throws IOException {
        Resource resource = new ClassPathResource("facebook-ads-key.properties");
        Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        return new APIContext(properties.getProperty("facebook.accessToken"), properties.getProperty("facebook.appSecret")).enableDebug(true);
    }
}
