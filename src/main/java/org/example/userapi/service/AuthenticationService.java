package org.example.userapi.service;

import org.example.userapi.model.auth.AuthenticationRequest;
import org.example.userapi.model.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(AuthenticationRequest authenticationRequest);
}
