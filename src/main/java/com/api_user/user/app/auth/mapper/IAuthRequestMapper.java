package com.api_user.user.app.auth.mapper;

import com.api_user.user.app.auth.dto.AuthRequest;
import com.api_user.user.domain.auth.model.Auth;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IAuthRequestMapper {
    Auth toAuth(AuthRequest authRequest);
}
