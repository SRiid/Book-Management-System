package com.example.BookManagementSpringBoot.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.BookManagementSpringBoot.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 var user = userRepo.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

	        var authority = new SimpleGrantedAuthority("ROLE_" + user.getRole()); // ROLE_ADMIN / ROLE_USER
	        return org.springframework.security.core.userdetails.User
	                .withUsername(user.getUsername())
	                .password(user.getPassword()) // BCrypt hash from DB
	                .authorities(authority)
	                .accountExpired(false).accountLocked(false)
	                .credentialsExpired(false).disabled(false)
	                .build();
	}

	
}
