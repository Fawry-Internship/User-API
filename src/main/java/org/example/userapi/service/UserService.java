package org.example.userapi.service;

import org.example.userapi.model.user.UserResponseDTO;

import java.util.List;

public interface UserService {
    String activateUser(Long userId);

    String deactivateUser(Long userId);

    List<UserResponseDTO> getAllUsers();
}
