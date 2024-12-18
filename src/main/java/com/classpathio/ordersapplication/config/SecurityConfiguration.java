package com.classpathio.ordersapplication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    // create a SecurityFilterChain that is customized to our needs
    // authorization
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //use the builder patern to configure the HttpSecurity object
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        authz -> authz
                                .requestMatchers("/h2-console/**", "/login**", "/logout", "/about-us").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/orders/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                                .requestMatchers(HttpMethod.POST, "/api/orders/**").hasAnyRole("ADMIN", "MODERATOR")
                                .requestMatchers(HttpMethod.DELETE, "/api/orders/**").hasRole("SUPER_ADMIN")
                                .anyRequest().fullyAuthenticated())
                .headers( header -> header.frameOptions(frame -> frame.disable())) // for h2-console
                .sessionManagement(session -> session.disable())
                .httpBasic(httpBasic -> {}) //use basic authentication.i.e username:password base64 encoded
                .formLogin(form -> form // form based authentication
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/home", true)
                        .failureUrl("/login?error=true")
                        .permitAll());
        return http.build();
    }

    //start with authentication
    //configure the UserDetailsService
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }


}
