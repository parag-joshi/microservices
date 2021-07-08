package com.fis.security.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fis.security.jwt.JwtRequestFilter;
import com.fis.security.user.MyUserDetailsService;

@EnableWebSecurity
public class LibrarySecurityConfigurerAdapter extends WebSecurityConfigurerAdapter 
{
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	MyUserDetailsService myUserDeatilsService;
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(myUserDeatilsService);
	}
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception 
	{
		httpSecurity.csrf().disable().authorizeRequests().antMatchers("/token").permitAll().anyRequest()
				.authenticated().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception 
	{
		return super.authenticationManagerBean();
	}

	
	@Bean
	public PasswordEncoder passwordEncoder() 
	{
		return NoOpPasswordEncoder.getInstance();
	}
}
