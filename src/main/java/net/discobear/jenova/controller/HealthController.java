package net.discobear.jenova.controller;

import javax.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@PermitAll
@Controller
public class HealthController {

  @GetMapping("/health-check")
  public ResponseEntity healthCheck() {
    return ResponseEntity.ok().build();
  }
}
