package com.ms.secservice.sec.config;

import com.auth0.jwt.JWT;
import com.ms.secservice.sec.filters.JwtAutorizationFilter;
import com.ms.secservice.sec.service.AccountService;
import com.ms.secservice.sec.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {

    private UserDetailsServiceImpl userDetailsService;
    private AccountService accountService;
    private PasswordEncoder passwordEncoder;
    private RsaKeysConfig rsaKeysConfig;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, AccountService accountService, PasswordEncoder passwordEncoder, RsaKeysConfig rsaKeysConfig) {
        this.userDetailsService = userDetailsService;
        this.accountService = accountService;
        this.passwordEncoder = passwordEncoder;
        this.rsaKeysConfig = rsaKeysConfig;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/api/v1/**")  // Disable CSRF for API endpoints
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                //.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService)
                .addFilterBefore(new JwtAutorizationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}