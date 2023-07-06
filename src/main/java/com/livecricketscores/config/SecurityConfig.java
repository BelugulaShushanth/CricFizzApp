package com.livecricketscores.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests( requests -> requests
                .antMatchers("/","/matches/**","/getMatches/**","/getMatch/**,/getMatchScoreCard/**"
                        ,"/error", "/resources/**","/static/**", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()
        );

        return httpSecurity.build();
    }

}
