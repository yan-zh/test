package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class PaperConfig {

    public void addResourHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/paper/**").addResourceLocations("file:" + System.getProperty("user.dir") + System.getProperty("file.separator") + "paper" + System.getProperty("file.separator"));
    }
}
