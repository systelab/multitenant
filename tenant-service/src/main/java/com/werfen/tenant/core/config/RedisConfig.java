package com.werfen.tenant.core.config;

import com.werfen.tenant.features.model.Tenant;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@ConfigurationProperties(prefix = "redis")
@Getter
@Setter
public class RedisConfig {

    private String host;
    private int port;

    @Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory() {
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public ReactiveRedisTemplate<String, Tenant> reactiveRedisTemplate() {
        Jackson2JsonRedisSerializer<Tenant> jackson2JsonSerializer = new Jackson2JsonRedisSerializer<>(Tenant.class);
        return new ReactiveRedisTemplate(reactiveRedisConnectionFactory(), RedisSerializationContext.fromSerializer(jackson2JsonSerializer));
    }

}
