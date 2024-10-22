package com.example.securityimpljwt.config;

import com.example.securityimpljwt.jwt.AuthEntryPointJwt;
import com.example.securityimpljwt.jwt.JwtFilter;
import com.example.securityimpljwt.util.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public JwtFilter authenticationJwtTokenFilter() {
        return new JwtFilter();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers("/h2-console").permitAll()
                    .requestMatchers("/signin").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                    .anyRequest().authenticated()
            );
        http.csrf(csrf -> csrf.disable());
        http.sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
        //for h2 console to show in browser
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
        http.authenticationManager(authenticationManager());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return new ProviderManager(Collections.singletonList(provider));
    }

}
