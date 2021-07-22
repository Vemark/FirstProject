package com.copart.web.request;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class JwtRequest {
  private String username;
  private String password;
  private String domain;

  public String getUsername() {
    if (StringUtils.isNotEmpty(domain)) {
      return domain + ":" + username;
    }
    return username;
  }
}
