package com.copart.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

  @RequestMapping("/ping")
  public ResponseEntity<?> firstPage() {
    return ResponseEntity.ok("{\"currentTime\": " + System.currentTimeMillis() + "}");
  }
}
