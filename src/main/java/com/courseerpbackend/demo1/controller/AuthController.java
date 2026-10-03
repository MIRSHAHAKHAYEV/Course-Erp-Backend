package com.courseerpbackend.demo1.controller;

import com.courseerpbackend.demo1.dto.response.BaseResponse;
import com.courseerpbackend.demo1.dto.response.LoginResponse;
import com.courseerpbackend.demo1.dto.response.RefreshTokenDto;
import com.courseerpbackend.demo1.dto.security.LoginPayload;
import com.courseerpbackend.demo1.entity.user.User;
import com.courseerpbackend.demo1.service.security.AccessTokenManager;
import com.courseerpbackend.demo1.service.security.RefreshTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload loginPayload){
        User user = User.builder().email("agaymirsah@gmail.com").build();
        user.setId(1L);
        return BaseResponse.success(
                LoginResponse.builder()
                        .accessToken(accessTokenManager.generate(user))
                        .refreshToken(refreshTokenManager.generate(
                                RefreshTokenDto.builder().user(user).isRememberMe(loginPayload.isRememberMe()).build()
                        ))
                        .build()
        );
    }
}
