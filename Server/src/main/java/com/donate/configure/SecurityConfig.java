package com.donate.configure;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.donate.security.JwtAuthenticationEntryPoint;
import com.donate.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	

	private UserDetailsService userDetailsService;
	
	
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	
	
	private JwtAuthenticationFilter authenticationFilter;
	
	
	public SecurityConfig (UserDetailsService userDetailsService,
							JwtAuthenticationEntryPoint authenticationEntryPoint,
							JwtAuthenticationFilter authenticationFilter) {
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}


    @Bean
    static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    	return configuration.getAuthenticationManager();
    }
    
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

        		.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> authorize
                		.requestMatchers("/api/**").permitAll()
                		.requestMatchers("/api/auth/**").permitAll()
                		.anyRequest()
                		.authenticated()
                ).exceptionHandling(exception -> exception
                		.authenticationEntryPoint(authenticationEntryPoint)
                ).sessionManagement(session -> session
                		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
		return http.build();
	}

}
