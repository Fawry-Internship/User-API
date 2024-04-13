package org.example.userapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.userapi.model.user.UserResponseDTO;
import org.example.userapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PutMapping("/activation/{userId}")
    public ResponseEntity<String> activateUser(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(userService.activateUser(userId), HttpStatus.ACCEPTED);
    }

    @PutMapping("/deactivation/{userId}")
    public ResponseEntity<String> deactivateUser(@PathVariable(name = "userId") Long userId){
        return new ResponseEntity<>(userService.deactivateUser(userId), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
}
