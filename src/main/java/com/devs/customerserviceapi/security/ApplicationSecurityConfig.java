package com.devs.customerserviceapi.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization","Cache-Control","Content-Type"));
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.setAllowedMethods(List.of("POST","DELETE","GET","PUT","PATCH","OPTION"));
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));

        http.csrf().disable()
                .cors().configurationSource(request -> corsConfiguration)
                .and()
                .authorizeHttpRequests()
                .antMatchers(
                        "/api/v1/users/register/**"
                ).permitAll()
                .anyRequest()
                .permitAll();

    }
}
