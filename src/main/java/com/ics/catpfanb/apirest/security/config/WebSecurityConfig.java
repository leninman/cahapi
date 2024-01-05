package com.ics.catpfanb.apirest.security.config;



import com.ics.catpfanb.apirest.security.jwt.JwtRequestFilter;
import com.ics.catpfanb.apirest.security.services.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioDetailsService usuarioDetailsService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.httpBasic(withDefaults()).authorizeRequests()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/security/authenticate").permitAll()
                .antMatchers("/security/user/**").hasAuthority("admin")
             //   .antMatchers("/app/getTblCheque").hasAnyAuthority("admin","owner","user")
             //   .antMatchers("/app/saveTblCheque").hasAnyAuthority("admin","owner")

                .anyRequest().authenticated()
                .and().cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(usuarioDetailsService);

       /* auth.inMemoryAuthentication().withUser("Tblcheque").password("{noop}"+"Microserviciotblechequepassword").roles("admin")
                .and()
                .withUser("user").password("{noop}"+"12345").roles("user");*/
    }
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
