package com.societe.navettes.securite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.societe.navettes.service.UserService;

@Configuration
public class SecuriteConfig {

	@Autowired
	UserService user_service;
	
	@Autowired
    private jwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain securityFilerChain(HttpSecurity http) throws Exception {
	
		return http
		.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request -> request
                 .requestMatchers("login", "register").permitAll()
                 .requestMatchers("/subscription/**").hasAnyAuthority("ADMIN","USER")
                 .requestMatchers("/**").hasAnyAuthority("ADMIN","USER")
                 .anyRequest().authenticated())
		.httpBasic(Customizer.withDefaults())
		.cors(Customizer.withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.build();
											//.requestMatchers("/users/**").hasAuthority("ADMIN")
											//.requestMatchers("/subscription/**").hasAuthority("ADMIN")
											//.requestMatchers("/autocar/**").hasAuthority("ADMIN"));
		
		 
	}
	
	@Bean
	AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        provider.setUserDetailsService(user_service);  
        return provider;
    }
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
	    CorsConfiguration configuration = new CorsConfiguration();
	    configuration.setAllowedOrigins(List.of("http://localhost:8080"));
	    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
	    configuration.setAllowCredentials(true);
	    configuration.addAllowedHeader("*");
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	    return source;
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();

	    }
	 
	
}
