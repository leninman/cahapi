package com.ics.catpfanb.apirest.security.config;


import com.ics.catpfanb.apirest.security.filter.JwtAuthenticationFilter;
import com.ics.catpfanb.apirest.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

   @Autowired
   private AuthenticationConfiguration authenticationConfiguration;

   @Bean
   AuthenticationManager authenticationManager() throws Exception {
      return authenticationConfiguration.getAuthenticationManager();
   }

   @Bean
   PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http.authorizeHttpRequests( (authz) -> authz
                      .requestMatchers(HttpMethod.POST,"usuarios/crear").hasRole("ADMIN")
                      .requestMatchers(HttpMethod.DELETE,"usuarios/eliminar/*").hasRole("ADMIN")
                      .requestMatchers(HttpMethod.POST,"usuarios/registrar").permitAll()
                      .requestMatchers(HttpMethod.GET,"usuarios/listar").permitAll()
                      .requestMatchers(HttpMethod.GET,"usuarios/consultar/**").permitAll()
                      .requestMatchers(HttpMethod.PUT,"usuarios/modificar/**").permitAll()

                      .requestMatchers("afiliado/**").permitAll()
                      .anyRequest().authenticated())
              .addFilter(new JwtAuthenticationFilter(authenticationManager()))  //Filtro de autenticacion
              .addFilter(new JwtValidationFilter(authenticationManager())) //Filtro de validacion
              .csrf(AbstractHttpConfigurer::disable)
              .cors(cors -> cors.configurationSource(corsConfigurationSource()))
              .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .build();
   }


   @Bean
   CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration config = new CorsConfiguration();
      config.setAllowedOriginPatterns(Arrays.asList("*"));
      config.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));
      config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
      config.setAllowCredentials(true);

      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
      return source;
   }

   @Bean
   FilterRegistrationBean<CorsFilter> corsFilter() {
      FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
              new CorsFilter(corsConfigurationSource()));
      corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
      return corsBean;
   }



}

