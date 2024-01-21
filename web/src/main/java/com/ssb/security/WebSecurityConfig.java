package com.ssb.security;

import com.ssb.data.*;
import com.ssb.service.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.*;
import org.springframework.web.servlet.handler.*;

@Configuration
@Import({DataConfiguration.class, ServiceConfiguration.class})
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

    @SuppressWarnings({"deprecation", "unused"})
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @SuppressWarnings({"unused"})
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/static/**", "/WEB-INF/jsp/**","/news/**", "/exchange-rates/convert-result","/requestForm", "/exchange-rates/convert").permitAll()
                        .requestMatchers("/login", "/api/**", "/register").anonymous()
                        .requestMatchers("/logout", "/home", "/deposits", "/addDeposit").authenticated()
                        .requestMatchers("/add**", "/delete**", "/update**", "/exchange-rates", "/callback-result").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .logout(Customizer.withDefaults())
        ;
        return http.build();
    }




    @SuppressWarnings({"unused"})
    @Bean
    public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
        return new HandlerMappingIntrospector();
    }
}
