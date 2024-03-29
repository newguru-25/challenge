package com.example.challenge.conf;

import com.example.challenge.handler.AuthenticationFaillHandler;
import com.example.challenge.handler.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfiguration {

  @Autowired
  private AuthenticationSuccessHandler authenticationSuccessHandler;

  @Autowired
  private AuthenticationFaillHandler authenticationFaillHandler;

  private static final String[] excludedAuthPages = {
      "/test/excludedAuthPages",
      "/swagger-ui/**",
      "/v2/api-docs/**",
      "/swagger-resources/**"
  };

  @Bean
  SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) {
    http.csrf(csrf -> csrf.disable())
        .authorizeExchange()
        .pathMatchers(excludedAuthPages).permitAll()
        .and()
        .authorizeExchange()
        .pathMatchers("/test/admin").hasRole("admin")
        .pathMatchers("/**").authenticated()
        .and()
        .formLogin()
        .authenticationSuccessHandler(authenticationSuccessHandler)
        .authenticationFailureHandler(authenticationFaillHandler)
        .and()
        .logout().disable();
    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(8);
  }
}
