package com.donate.configure;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Adjust the mapping pattern as needed
            .allowedOrigins("*") // Adjust the allowed origins as needed
            .allowedMethods("GET", "POST", "PUT", "DELETE") // Adjust the allowed methods as needed
            .allowedHeaders("*"); // Adjust the allowed headers as needed
    }
}
