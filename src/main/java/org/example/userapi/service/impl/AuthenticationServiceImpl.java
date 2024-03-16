package org.example.userapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userapi.entity.User;
import org.example.userapi.exception.ConflictException;
import org.example.userapi.mapper.UserMapper;
import org.example.userapi.model.auth.AuthenticationRequest;
import org.example.userapi.model.auth.AuthenticationResponse;
import org.example.userapi.repository.UserRepository;
import org.example.userapi.security.JWTService;
import org.example.userapi.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        log.info("user wants to login with this credentials {}", authenticationRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        User user = userRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        var jwtToken = jwtService.generateToken(authentication);
        log.info("The user has login successfully");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @Override
    public AuthenticationResponse register(AuthenticationRequest authenticationRequest) {
        User user = userMapper.toEntity(authenticationRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("This is user {}", user);

        if(userRepository.findByEmail(authenticationRequest.getEmail()).isPresent()){
            log.error("this User already exist");
            throw new ConflictException("this User already exist");
        }

        userRepository.save(user);
        log.info("User Added Successfully {}", user);
        return authenticate(new AuthenticationRequest(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
    }
}
