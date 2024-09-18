package com.api_user.user.domain.user.spi;

public interface IPasswordEncoderPort {
    String encode(String password);
}
