package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(@NonNull PathMatchConfigurer configurer) {
//        configurer.setUseRegisteredSuffixPatternMatch(true);
    }
}
