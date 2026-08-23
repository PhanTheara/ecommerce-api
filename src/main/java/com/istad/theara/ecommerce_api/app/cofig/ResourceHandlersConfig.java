package com.istad.theara.ecommerce_api.app.cofig;

import com.istad.theara.ecommerce_api.features.util.ResourcePrefix;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlersConfig implements WebMvcConfigurer {


    @Value("${media.location}")
    private String mediaLocation;

    @Value("${media.client-path}")
    private String mediaClientPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(mediaClientPath + "/**").addResourceLocations(ResourcePrefix.FILE_SYSTEM + mediaLocation);
    }

}
