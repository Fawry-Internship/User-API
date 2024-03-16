package org.example.userapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.userapi.entity.User;
import org.example.userapi.exception.RecordNotFoundException;
import org.example.userapi.exception.UpdateNotFoundException;
import org.example.userapi.mapper.UserMapper;
import org.example.userapi.model.user.UserResponseDTO;
import org.example.userapi.repository.UserRepository;
import org.example.userapi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public String activateUser(Long userId) {
        log.info("Admin want to activate user with id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("This user with id " + userId + " doesn't exist"));
        if(user.isEnable()){
            log.error("This user with id {}, Already Active", userId);
            throw new UpdateNotFoundException("This User with id "+ userId + " Already Active!");
        }
        user.setEnable(true);
        userRepository.save(user);

        log.info("The User is Active Now and This enable Status {}", user.isEnable());
        return "Activation Success";
    }

    @Override
    public String deactivateUser(Long userId) {
        log.info("Admin want to deactivate user with id {}", userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RecordNotFoundException("This user with id " + userId + " doesn't exist"));
        if(!user.isEnable()){
            log.error("This user with id {}, Already Inactive", userId);
            throw new UpdateNotFoundException("This User with id "+ userId + " Already Inactive!");
        }
        user.setEnable(false);
        userRepository.save(user);

        log.info("The User is Inactive Now and This enable Status {}", user.isEnable());
        return "Deactivation Success";
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        log.info("Admin want to get All Users");
        List<UserResponseDTO> usersDTOs = userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());

        if(usersDTOs.isEmpty()){
            log.error("Not Found any user");
            throw new RecordNotFoundException("Not Found any user");
        }

        log.info("All users {}", usersDTOs);
        return usersDTOs;
    }
}
