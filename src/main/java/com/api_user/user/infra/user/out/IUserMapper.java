package com.api_user.user.infra.user.out;

import com.api_user.user.domain.user.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    UserEntity toEntity(User user);

    User toUser(UserEntity userEntity);
}
