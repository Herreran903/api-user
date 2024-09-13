package com.api_user.user.utils;

import java.time.LocalDate;

public final class TestConstants {

    private TestConstants() {
        throw new AssertionError();
    }

    public static final Long VALID_USER_ID = 1L;
    public static final String VALID_USER_NAME = "test";
    public static final String VALID_USER_LAST_NAME = "test";
    public static final String VALID_USER_DNI = "123456789";
    public static final String VALID_USER_PHONE = "+57123456789";
    public static final LocalDate VALID_USER_BIRTHDATE = LocalDate.parse("2000-10-10");
    public static final String VALID_USER_EMAIL = "test@test.com";
    public static final String VALID_USER_PASSWORD = "123456";
    public static final String VALID_USER_ROLE = "ROLE_WAREHOUSE_ASSISTANT";

    public static final Long VALID_ROLE_ID = 1L;
    public static final String ROLE_WAREHOUSE_ASSISTANT_NAME = "ROLE_WAREHOUSE_ASSISTANT";
    public static final String ROLE_WAREHOUSE_ASSISTANT_DESC = "ROLE_WAREHOUSE_ASSISTANT";
    public static final String ROLE_CLIENT_NAME = "ROLE_CLIENT_NAME";
    public static final String ROLE_CLIENT_DESC = "ROLE_CLIENT_NAME";
}
