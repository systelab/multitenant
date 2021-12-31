package com.werfen.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .pathMatchers("/swagger").permitAll()
                .pathMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                .pathMatchers("/favicon.ico").permitAll()
                .pathMatchers("/webjars/**").permitAll()
                .pathMatchers("/", "/index.html").permitAll()
                .pathMatchers("/*.js", "/*.css").permitAll()
                .pathMatchers("/*.gif", "/*.png", "/*.svg").permitAll()
                .pathMatchers("/*.woff2", "/*.woff", "/*.eot", "/*.ttf").permitAll()
                .pathMatchers("/assets/**", "/i18n/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer().jwt();
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setMaxAge(3600L);
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
    }
}