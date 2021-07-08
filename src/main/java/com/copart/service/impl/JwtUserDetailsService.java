package com.copart.service.impl;

import com.copart.entity.AppUsers;
import com.copart.model.CopartUserDetails;
import com.copart.repository.AppUsersRepository;
import com.copart.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class JwtUserDetailsService implements UserDetailsService {

  @Autowired private UserRepository userRepository;
  @Autowired private AppUsersRepository appUsersRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    if (username.contains(":")) {
      String domain = username.split(":")[0];
      username = username.split(":")[1];

      AppUsers domainUser = appUsersRepository.findUser(username, domain);
      if (domainUser == null) {
        throw new UsernameNotFoundException("User not found with username: " + username);
      }
      final CopartUserDetails userDetails = new CopartUserDetails();
      userDetails.setUsername(domainUser.getUsername());
      userDetails.setPassword(domainUser.getPasswordHash() + "");
      userDetails.setDomain(domainUser.getLoginDomain());
      return userDetails;
    }
    com.copart.entity.User user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    final CopartUserDetails userDetails = new CopartUserDetails();
    userDetails.setUsername(user.getUsername());
    userDetails.setPassword(user.getPassword());
    return userDetails;
  }
}
