package com.werfen.inventory.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin(); // Enable h2 console. Not for production

        http.csrf().disable().
                authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/h2/**").permitAll()
                .antMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**", "/swagger-resources/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/", "/index.html").permitAll()
                .antMatchers("/*.js", "/*.css").permitAll()
                .antMatchers("/*.gif", "/*.png", "/*.svg").permitAll()
                .antMatchers("/*.woff2", "/*.woff", "/*.eot", "/*.ttf").permitAll()
                .antMatchers("/assets/**", "/i18n/**").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/api/v1/**").permitAll()
                .antMatchers("/api/v1/**").permitAll()

                .anyRequest().authenticated()
                .and().oauth2ResourceServer().jwt(jwt -> {
                    jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor());
                })
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private JwtAuthenticationConverter grantedAuthoritiesExtractor() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
                    jwt.getClaims().forEach((claim, value) -> System.out.println(claim + "=" + value));
                    /*
                    if (!jwt.getClaims().containsKey("cognito:groups")) {
                        throw new SecurityException("group not present");
                    }

                     */
                    return getGroups(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
                }
        );
        return jwtAuthenticationConverter;
    }

    private List<String> getGroups(Jwt jwt) {
        return (List<String>) jwt.getClaims().getOrDefault("cognito:groups", Collections.emptyList());
    }

}
