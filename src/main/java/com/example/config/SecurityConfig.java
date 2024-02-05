package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.constant.Role;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf) -> csrf
				.ignoringRequestMatchers("/h2-console/**"))
			.headers((headers) -> headers
					.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))

			.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
					.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
					.requestMatchers("/h2-console/**").permitAll()
					.requestMatchers("/", "/members/**", "/items/**", "/images/**").permitAll()
					.requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.toString())
					.anyRequest().authenticated())
			
			.formLogin((formLogin) -> formLogin
					.loginPage("/members/login")
					.defaultSuccessUrl("/")
					.usernameParameter("email")
					.failureUrl("/members/login/error"))
			
			.logout((logout) -> logout 
					.logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))
					.logoutSuccessUrl("/")
					.invalidateHttpSession(true))
			.exceptionHandling((exceptionHandling) -> exceptionHandling
					.authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")));
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
