package com.udemy.spring.security.service.session02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ServiceAppSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterSecurityChain(HttpSecurity http) throws Exception {
    // ------------------------------------------
    http.authorizeHttpRequests((req) -> req
                    //.anyRequest().authenticated();
                    //.anyRequest().denyAll()
                    .requestMatchers("/myAccount/**", "/myBalance",
                            "/myLoads", "/myCards").authenticated()
                    // --------------------------------------------------------
                    .requestMatchers("/notices", "/contact").permitAll())
            // -------------------------------------------------------------------
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
    // ---------------------------------------------------------------
    return http.build();
  }

}
