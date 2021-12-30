package com.werfen.laboratory.core.config;

import com.werfen.laboratory.core.tenant.RequestTenantInterceptor;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.undertow.Undertow.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebServerConfig implements WebMvcConfigurer {

    private final HttpServerPortConfig httpServerPortConfig;
    private final RequestTenantInterceptor requestTenantInterceptor;

    @Bean
    public WebServerFactoryCustomizer<UndertowServletWebServerFactory> undertowListenerCustomizer() {
        return factory -> factory.addBuilderCustomizers(this::addHttpListener);
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
        return factory -> factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error"));
    }

    private Builder addHttpListener(Builder builder) {
        int port = httpServerPortConfig.getPort();

        if (port > 0) {
            return builder.addHttpListener(port, "0.0.0.0");
        } else {
            return builder;
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("forward:/index.html");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestTenantInterceptor);
    }

}