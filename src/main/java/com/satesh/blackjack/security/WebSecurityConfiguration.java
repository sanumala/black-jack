package com.satesh.blackjack.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Configures authentication controls.
 * <p>
 * <ol>
 * <li>Configures the use of Basic authentication. Basic authentication over HTTP is not secure as the base 64-encoded
 * string is easily decoded. TODO A production system would need to enforce transport-level security using HTTPS. An
 * alternative to Basic authentication is Digest authentication which has the advantages of avoiding sending passwords
 * over the network in an easily compromised form and providing a defence against replay attacks.</li>
 * <li>Disables cross-site request forgery (CSRF) protection. Disabling on assumption that requests will be made only by
 * non-browser clients (e.g. a mobile application). TODO If requests are to be processed directly from a browser by
 * normal users (i.e. as part of an Ajax web-app) then we would want to enable CSRF protection.</li>
 * <ol>
 *
 * @see http://docs.spring.io/autorepo/docs/spring-security/3.2.0.CI-SNAPSHOT/reference/html/csrf.html</li>
 */
@Configuration
class WebSecurityConfiguration {
   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      http
              .authorizeHttpRequests((authz) -> authz
                      .anyRequest().authenticated()
              )
              .httpBasic(withDefaults());
      return http.build();
   }

   @Bean
   public static BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
