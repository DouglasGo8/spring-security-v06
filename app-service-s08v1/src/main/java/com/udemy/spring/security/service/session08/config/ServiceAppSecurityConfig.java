package com.udemy.spring.security.service.session08.config;

import com.udemy.spring.security.service.session08.filter.AuthoritiesLoggingAfterFilter;
import com.udemy.spring.security.service.session08.filter.AuthoritiesLoggingFilter;
import com.udemy.spring.security.service.session08.filter.CSRFCookieFilter;
import com.udemy.spring.security.service.session08.filter.RequestValidationBeforeFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class ServiceAppSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    var requestHandler = new CsrfTokenRequestAttributeHandler();
    requestHandler.setCsrfRequestAttributeName("_csrf");
    http.securityContext((context) -> context.requireExplicitSave(false))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
              @Override
              public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
              }
            })).csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            // ---------------------------------- CUSTOM FILTERS ----------------------------------
            .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)
            .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
            .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
            .addFilterAt(new AuthoritiesLoggingFilter(), BasicAuthenticationFilter.class)
            // ---------------------------------------------------------------------------------------
            .authorizeHttpRequests((requests) -> requests
                    /*.requestMatchers("/myAccount").hasAuthority("VIEWACCOUNT")
                    .requestMatchers("/myBalance").hasAnyAuthority("VIEWACCOUNT","VIEWBALANCE")
                    .requestMatchers("/myLoans").hasAuthority("VIEWLOANS")
                    .requestMatchers("/myCards").hasAuthority("VIEWCARDS")*/
                    .requestMatchers("/myAccount").hasRole("USER")
                    .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/myLoans").hasRole("USER")
                    .requestMatchers("/myCards").hasRole("USER")
                    .requestMatchers("/user").authenticated()
                    .requestMatchers("/notices", "/contact", "/register").permitAll())
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());
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
