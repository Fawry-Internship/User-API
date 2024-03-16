package org.example.userapi.mapper;

import org.example.userapi.entity.User;
import org.example.userapi.model.auth.AuthenticationRequest;
import org.example.userapi.model.user.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserResponseDTO toDTO(User user);

    User toEntity(AuthenticationRequest AuthenticationRequest);
}
