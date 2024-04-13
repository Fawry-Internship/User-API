package org.example.userapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("users/token/validation")
public class ValidationController {
    @GetMapping
    public ResponseEntity<String> validateToken(){
        return ResponseEntity.ok("success");
    }
}
