package com.copart.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class JwtUserDetailsService implements UserDetailsService {
  private final static String DEFAULT_USERNAME = "admin";
  private final static String DEFAULT_PASSWORD = "password";
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    if (DEFAULT_USERNAME.equals(username)) {
      String hashedPassword =
          passwordEncoder.encode(
                  DEFAULT_PASSWORD);
      return new User(DEFAULT_USERNAME, hashedPassword, Collections.emptyList());
    }
    throw new UsernameNotFoundException("User not found with username: " + username);
  }
}
