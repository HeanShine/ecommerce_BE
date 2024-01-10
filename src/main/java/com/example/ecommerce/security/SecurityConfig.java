package com.example.ecommerce.security;

import com.example.ecommerce.security.jwt.CustomAccessDeniedHandler;
import com.example.ecommerce.security.jwt.AuthenticationFilter;
import com.example.ecommerce.security.jwt.RestAuthenticationEntryPoint;
import com.example.ecommerce.service.impl.AccountServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean // bean la 1 doi tuong duoc quan ly boi spring container (IOC) va duoc quan ly boi spring container
    public AccountServiceImpl accountService() {
        return new AccountServiceImpl();
    }

    @Bean
    public AuthenticationFilter jwtAuthenticationFilter() {
        return new AuthenticationFilter();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }


    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() { // dung de xu ly loi 403
        return new CustomAccessDeniedHandler();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(accountService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/updateAccount").permitAll()
                        .requestMatchers("/role/**").permitAll()
                        .requestMatchers("/getAllAccount").hasAuthority("ROLE_ADMIN")
                        .requestMatchers("/shop/**").permitAll()
                        .requestMatchers("/category/**").permitAll()
                        .requestMatchers("/product/**").permitAll()
                        .requestMatchers("/imageDetailProduct/**").permitAll()
                        .requestMatchers("/comment/**").permitAll()
                        .requestMatchers("/changePassword").permitAll()
                        .requestMatchers("/order/**").permitAll()
                        .requestMatchers("/orderDetail/**").permitAll())

                .exceptionHandling(customizer -> customizer.accessDeniedHandler(customAccessDeniedHandler()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }

}
