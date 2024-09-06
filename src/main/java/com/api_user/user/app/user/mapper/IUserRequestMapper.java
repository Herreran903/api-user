package com.api_user.user.app.user.mapper;

import com.api_user.user.app.user.dto.UserRequest;
import com.api_user.user.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    default User toUser(UserRequest userRequest){
        return new User.Builder()
                .name(userRequest.getName())
                .lastname(userRequest.getLastname())
                .dni(userRequest.getDni())
                .phone(userRequest.getPhone())
                .birthdate(userRequest.getBirthdate())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();
    }
}
