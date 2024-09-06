package com.api_user.user.infra.user.out;

import com.api_user.user.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    @Mapping(source = "role", target = "role")
    UserEntity toEntity(User user);
}
