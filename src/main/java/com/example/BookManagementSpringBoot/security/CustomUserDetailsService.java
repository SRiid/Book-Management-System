package com.example.BookManagementSpringBoot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BookManagementSpringBoot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepo;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 var user = userRepo.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

		 return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword()) 
	                .roles(user.getRole()) 
	                .build();
	}

	
}
