package com.copart.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

  @RequestMapping("/hello")
  public ResponseEntity<String> firstPage() {
    return ResponseEntity.ok("Hello World");
  }
}
