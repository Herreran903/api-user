package com.api_user.user.domain.user.usecase;

import com.api_user.user.domain.role.api.IRoleServicePort;
import com.api_user.user.domain.role.model.Role;
import com.api_user.user.domain.role.util.RoleEnum;
import com.api_user.user.domain.user.api.IUserServicePort;
import com.api_user.user.domain.error.ErrorDetail;
import com.api_user.user.domain.user.exception.UserExceptionMessage;
import com.api_user.user.domain.user.exception.ex.UserAlreadyExistException;
import com.api_user.user.domain.user.exception.ex.UserNotValidFieldException;
import com.api_user.user.domain.user.model.User;
import com.api_user.user.domain.user.spi.IPasswordEncoderPort;
import com.api_user.user.domain.user.spi.IUserPersistencePort;
import com.api_user.user.domain.user.util.UserConstants;
import com.api_user.user.domain.user.validation.UserValidator;
import com.api_user.user.domain.util.GlobalExceptionMessage;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IPasswordEncoderPort passwordEncoderPort;
    private final IRoleServicePort roleServicePort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IPasswordEncoderPort passwordEncoderPort, IRoleServicePort roleServicePort) {
        this.userPersistencePort = userPersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
        this.roleServicePort = roleServicePort;
    }

    private void validateUser(User user) {
        List<ErrorDetail> errors = UserValidator.validateUser(user);

        if (!errors.isEmpty())
            throw new UserNotValidFieldException(GlobalExceptionMessage.INVALID_OBJECT, errors);

        if(Boolean.TRUE.equals(userPersistencePort.isUserPresentByDni(user.getDni())))
            errors.add(new ErrorDetail(UserConstants.DNI, String.format(UserExceptionMessage.USER_ALREADY_EXISTS_DNI, user.getDni())));

        if(Boolean.TRUE.equals(userPersistencePort.isUserPresentByEmail(user.getEmail())))
            errors.add(new ErrorDetail(UserConstants.EMAIL, String.format(UserExceptionMessage.USER_ALREADY_EXISTS_EMAIL, user.getEmail())));

        if (!errors.isEmpty())
            throw new UserAlreadyExistException(GlobalExceptionMessage.ALREADY_EXIST_OBJECT, errors);

    }

    @Override
    public void createUserWarehouseAssistant(User user) {
        validateUser(user);

        Role role = roleServicePort.getRoleByName(RoleEnum.ROLE_WAREHOUSE_ASSISTANT);
        user.setRole(role);

        String encodePassword = passwordEncoderPort.encode(user.getPassword());
        user.setPassword(encodePassword);

        userPersistencePort.createUser(user);
    }
}
