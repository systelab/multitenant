package com.werfen.laboratory.core.security.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "security")
public class CorsConfig {

    private final CorsConfiguration cors = new CorsConfiguration();
    private final Log logger = LogFactory.getLog(this.getClass());

    public CorsConfiguration getCors() {
        return cors;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        List<String> allowedOrigins=cors.getAllowedOrigins();
        if (allowedOrigins != null && !allowedOrigins.isEmpty()) {
            logger.debug("Registering CORS filter");
            source.registerCorsConfiguration("/api/**", cors);
            source.registerCorsConfiguration("/v3/api-docs", cors);
        }
        return new CorsFilter(source);
    }
}
