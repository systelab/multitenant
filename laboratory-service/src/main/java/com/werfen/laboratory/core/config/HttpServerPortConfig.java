package com.werfen.laboratory.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "http-server")
@Getter
@Setter
public class HttpServerPortConfig
{

    private int port;
}