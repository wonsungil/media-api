package com.bbp.bbptest.google.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.ads.googleads.lib.GoogleAdsClient;

@Configuration
public class GoogleAdsConfig {

    @Bean(name = "googleAdsClient")
    public GoogleAdsClient googleAdsClient() throws IOException {
        Resource resource = new ClassPathResource("google-ads-oauth2.properties");
        return GoogleAdsClient.newBuilder()
                .fromPropertiesFile(resource.getFile())
                .setLoginCustomerId(Constants.GOOGLE_ROOT_CUSTOMER_ID)
                .setDeveloperToken(Constants.DEVELOPER_TOKEN)
                .build();
    }

    @Bean(name = "googleCredential")
    public Resource googleCredential() {
        return new ClassPathResource("google-ads-oauth2.properties");
    }
}
