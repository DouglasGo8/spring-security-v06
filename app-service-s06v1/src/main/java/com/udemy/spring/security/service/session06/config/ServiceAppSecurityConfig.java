package com.udemy.spring.security.service.session06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ServiceAppSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterSecurityChain(HttpSecurity http) throws Exception {
    // ------------------------------------------
    http.csrf().disable() // (csrf) => allows PostMapping methods
            .authorizeHttpRequests((req) -> req
                    //.anyRequest().authenticated();
                    //.anyRequest().denyAll()
                    .requestMatchers("/myAccount/**", "/myBalance", "/myLoads", "/myCards").authenticated()
                    // --------------------------------------------------------
                    .requestMatchers("/notices", "/contact", "/register").permitAll())

            // -------------------------------------------------------------------
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
    // ---------------------------------------------------------------
    return http.build();
  }

  // Only demos purpose isn't recommended for production
  //@Bean
  InMemoryUserDetailsManager userDetailsManager() {
    var admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("welcome1")
            .authorities("admin")
            .build();
    //
    var user = User.withDefaultPasswordEncoder()
            .username("guest")
            .password("12345")
            .authorities("read")
            .build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  // Only demos purpose isn't recommended for production
  //@Bean
  InMemoryUserDetailsManager userDetailsManagerNoOpPwdEncoder() {

    var admin = User.withUsername("admin")
            .password("welcome1")
            .authorities("admin")
            .build();
    //
    var user = User.withUsername("guest")
            .password("12345")
            .authorities("read")
            .build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  // Not recommended for production
  // Mandatory for jdbcUserDetailsManager
  // @Bean
  //PasswordEncoder passwordEncoder() {
  // Only demos purpose isn't recommended for production
  //  return NoOpPasswordEncoder.getInstance();
  //}

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //@Bean
  UserDetailsService userDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);

  }


}
