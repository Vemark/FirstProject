package com.copart.web.request;

import lombok.Data;

@Data
public class JwtRequest {
  private String username;
  private String password;
  private String domain;
}
