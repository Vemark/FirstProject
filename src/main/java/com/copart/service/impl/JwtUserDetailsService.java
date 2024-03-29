package com.copart.service.impl;

import com.copart.entity.AppUsers;
import com.copart.entity.PlLoginInfo;
import com.copart.model.CopartUserDetails;
import com.copart.repository.AppUsersRepository;
import com.copart.repository.PlLoginInfoRepository;
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
  @Autowired private PlLoginInfoRepository plLoginInfoRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    if (StringUtils.isEmpty(username)) {
      throw new UsernameNotFoundException("Username is empty");
    }
    if (username.contains(":")) {
      return findInAppUsers(username);
    }
    UserDetails userDetails = findInUsers(username);
    if (userDetails == null) {
      userDetails = findPlLoginInfo(username);
    }
    if (userDetails == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    return userDetails;
  }

  private UserDetails findInAppUsers(String username) {
    String domain = username.split(":")[0];
    username = username.split(":")[1];

    AppUsers domainUser = appUsersRepository.findUser(username, domain);
    if (domainUser == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    final CopartUserDetails userDetails = new CopartUserDetails();
    userDetails.setId(domainUser.getId());
    userDetails.setUsername(domainUser.getUsername());
    userDetails.setPassword(domainUser.getPasswordHash() + "");
    userDetails.setPasswordHashed(true);
    userDetails.setDomain(domainUser.getLoginDomain());
    return userDetails;
  }

  private UserDetails findInUsers(String username) {
    com.copart.entity.User user = userRepository.findByUsername(username);
    if (user == null) {
      return null;
    }
    final CopartUserDetails userDetails = new CopartUserDetails();
    userDetails.setId(userDetails.getId());
    userDetails.setUsername(user.getUsername());
    userDetails.setPassword(user.getPassword());
    return userDetails;
  }

  private UserDetails findPlLoginInfo(String username) {
    PlLoginInfo plLoginInfo = plLoginInfoRepository.findByUsername(username);
    if (plLoginInfo == null) {
      return null;
    }
    final CopartUserDetails userDetails = new CopartUserDetails();
    userDetails.setId(plLoginInfo.getId());
    userDetails.setUsername(plLoginInfo.getLoginName());
    userDetails.setPassword(plLoginInfo.getPassword());
    return userDetails;
  }
}
