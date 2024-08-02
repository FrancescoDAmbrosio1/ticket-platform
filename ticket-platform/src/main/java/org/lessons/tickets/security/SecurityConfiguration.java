package org.lessons.tickets.security;

import org.lessons.tickets.service.DatabaseUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	  @Bean
	  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		    http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
		        .requestMatchers("/main", "/main/create/**", "/main/delete/**", "/user").hasAuthority("ADMIN")
		        .requestMatchers("note/create").hasAnyAuthority("ADMIN", "OPERATORE")
		    	.requestMatchers("/css/**", "/js/**", "/webjars/**","/images/**").permitAll()
		    	.anyRequest().permitAll());
		    	http.formLogin(login -> login
		    			.defaultSuccessUrl("/user/home",true))
		    	.logout().permitAll();
		    ;
	    return http.build();
	  }
	  
	  @Bean
	  DatabaseUserDetailsService userDetailsService() {
	    
		  return new DatabaseUserDetailsService();
	  }

	  @Bean
	  PasswordEncoder passwordEncoder() {
	    
		  return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	  }

	  @Bean
	  DaoAuthenticationProvider authenticationProvider() {
	    
		  DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    
		  authProvider.setUserDetailsService(userDetailsService());
	    
		  authProvider.setPasswordEncoder(passwordEncoder());
	    
		  return authProvider;
	  }
}
