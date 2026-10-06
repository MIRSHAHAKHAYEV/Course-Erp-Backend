package com.courseerpbackend.demo1.service.security;

import com.courseerpbackend.demo1.dto.response.LoginResponse;
import com.courseerpbackend.demo1.dto.security.LoginPayload;
import com.courseerpbackend.demo1.dto.security.RefreshTokenPayload;

public interface IAuthBusinessService {

    LoginResponse login(LoginPayload loginPayload);

    LoginResponse refresh(RefreshTokenPayload refreshTokenPayload);

    void logout();

    void setAuthentication(String email);
}
