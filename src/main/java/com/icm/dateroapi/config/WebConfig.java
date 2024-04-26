package com.icm.dateroapi.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET","POST","PUT","DELETE")
                .allowedHeaders("*")
                .maxAge(3600);
    }
}

/*

                .allowedOrigins("http://localhost:8084","http://192.168.0.214", "http://192.168.0.214:8084/*", "http://181.224.251.187:8084",
                        "http://localhost:3000")
 */