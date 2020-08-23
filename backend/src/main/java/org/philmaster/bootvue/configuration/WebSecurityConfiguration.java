package org.philmaster.bootvue.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailService;

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring
																		// security
//				.and()
//				.httpBasic()
				.and()
				.authorizeRequests()
				.antMatchers("/**")
				.permitAll()
				.antMatchers("/**")
				.permitAll() // allow every URI, that begins with '/api/user/'
				.antMatchers("/api/secured")
				.authenticated()
				// .anyRequest().authenticated() // protect all other requests
				.and()
				.csrf()
				.disable();

//		http.sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No session will be created or used by spring
//																		// security
//				.and()
//				.httpBasic()
//				.and()
//				.authorizeRequests()
//				.antMatchers("/api/hello")
//				.permitAll()
//				.antMatchers("/api/user/**")
//				.permitAll() // allow every URI, that begins with '/api/user/'
//				.antMatchers("/api/secured")
//				.authenticated()
//				// .anyRequest().authenticated() // protect all other requests
//				.and()
//				.csrf()
//				.disable(); // disable cross site request forgery, as we don't use cookies - otherwise ALL
////		// PUT, POST, DELETE will get HTTP 403!
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("sa")
//				.password("{noop}1")
//				.roles("USER");
//	}

}
