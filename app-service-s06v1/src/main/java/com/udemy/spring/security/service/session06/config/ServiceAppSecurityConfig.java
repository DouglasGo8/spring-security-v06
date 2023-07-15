package com.udemy.spring.security.service.session06.config;

import com.udemy.spring.security.service.session06.filter.CSRFCookieFilter;
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
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.security.web.csrf.CsrfTokenRequestHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class ServiceAppSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterSecurityChain(HttpSecurity http) throws Exception {

    // ----------------------------------------------------------
    var requestHandler = new CsrfTokenRequestAttributeHandler();
    requestHandler.setCsrfRequestAttributeName("_csrf"); //default alias
    // ---------------------------------------------------------------------
    http.securityContext().requireExplicitSave(false) // false means JSESSIONID will be responsible to manage this
            .and().sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
            .cors().configurationSource(request -> {
              //
              var config = new CorsConfiguration();
              //
              config.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // ReactJS app address
              config.setAllowedMethods(Collections.singletonList("*"));
              config.setAllowCredentials(true);
              config.setAllowedHeaders(Collections.singletonList("*"));
              config.setMaxAge(3600L);
              return config;
            }).and()
            //.csrf().ignoringRequestMatchers("/contact", "/register")
            .csrf((c) -> c.csrfTokenRequestHandler(requestHandler)
                    .ignoringRequestMatchers("/contact", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            .addFilterAfter(new CSRFCookieFilter(), BasicAuthenticationFilter.class)
            //.and()
            /*.csrf().disable()*/ // (csrf) => allows PostMapping methods, not recommend to production
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
