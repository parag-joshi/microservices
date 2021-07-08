package com.fis.security.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fis.security.http.AuthenticationRequest;
import com.fis.security.http.AuthenticationResponse;
import com.fis.security.jwt.JwtUtil;
import com.fis.security.user.MyUserDetailsService;

@RestController
public class LibraryAuthenticationResource 
{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private MyUserDetailsService userDetailsService;
	
	@Autowired
	private JwtUtil jwtToken;
	
	
	@GetMapping("/hello")
	public String helloFIS() 
	{
		return "Hello FIS!";
	}
	
	@PostMapping("/token")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception 
	{

		// authenticating user credentials
		try 
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} 
		catch (BadCredentialsException e) 
		{
			throw new Exception("Incorrect username or password", e);
		}

		// if user has been successfully authenticated, generate token.

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtToken.generateToken(userDetails);
		
		// send token in response.
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
}
