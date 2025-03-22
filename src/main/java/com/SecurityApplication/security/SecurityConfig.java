
package com.SecurityApplication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity //Using this we can implement the method level security or role base authentication
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // Disable CSRF for REST API
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/login").permitAll() // Allow login API
						.anyRequest().authenticated() // Secure all other endpoints
				).sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // No
																												// sessions
				.httpBasic(Customizer.withDefaults());
		// Enable Basic Authentication

		return http.build();
	}
        //Differnce between 401 and 403   : 
	//401 : Client hasn't valid authentication credentials
	//403 : Forbidden error means the client has valid credentials but lacks of necessary permissions to access the request resource 
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user1 = User.withUsername("user1").password("{noop}password1").roles("USER").build();

		UserDetails admin = User.withUsername("admin").password("{noop}adminPass").roles("ADMIN").build();

		return new InMemoryUserDetailsManager(user1, admin);
	}
}
