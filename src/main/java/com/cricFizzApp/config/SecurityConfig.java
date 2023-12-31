package com.cricFizzApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests( requests -> requests
                .antMatchers("/matches/**","/getMatches/**","/getMatch/**,/getMatchScoreCard/**"
                        ,"/error", "/resources/**","/static/**", "/css/**", "/js/**", "/images/**"
                        ,"/fonts/**","/vendor/**","/createAccount/**", "/verifyAccount/**"
                        ,"/loginUser/**").permitAll()
                .anyRequest().authenticated()
        )
        .oauth2Login()
        .defaultSuccessUrl("/alerts/dashboard")
        .failureUrl("/")
                .and()
                .logout()
                .logoutUrl("/logoutUser")
                .logoutSuccessUrl("/logoutSuccess")
        .and()
                .formLogin()
                .loginPage("/loginUser")
                .loginProcessingUrl("/loginUser")
                .defaultSuccessUrl("/alerts/dashboard")
                .failureUrl("/loginUser?error=true")
                .and()
                .logout()
                .logoutUrl("/logoutUser")
                .logoutSuccessUrl("/logoutSuccess");

        return httpSecurity.build();
    }

}
