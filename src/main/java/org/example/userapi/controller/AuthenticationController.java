package org.example.userapi.controller;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.userapi.model.auth.AuthenticationRequest;
import org.example.userapi.model.auth.AuthenticationResponse;
import org.example.userapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse response = authenticationService.register(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}
