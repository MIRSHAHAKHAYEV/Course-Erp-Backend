package com.courseerpbackend.demo1.controller;

import com.courseerpbackend.demo1.dto.response.BaseResponse;
import com.courseerpbackend.demo1.dto.response.LoginResponse;
import com.courseerpbackend.demo1.dto.response.RefreshTokenDto;
import com.courseerpbackend.demo1.dto.security.LoginPayload;
import com.courseerpbackend.demo1.dto.security.RefreshTokenPayload;
import com.courseerpbackend.demo1.entity.user.User;
import com.courseerpbackend.demo1.service.security.AccessTokenManager;
import com.courseerpbackend.demo1.service.security.IAuthBusinessService;
import com.courseerpbackend.demo1.service.security.RefreshTokenManager;
import com.courseerpbackend.demo1.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthBusinessService authBusinessService;

    @PostMapping("/login")
    public BaseResponse<LoginResponse> login(@RequestBody LoginPayload loginPayload) {
        return BaseResponse.success(authBusinessService.login(loginPayload));
    }
    @PostMapping("/token/refresh")
    public BaseResponse<LoginResponse> refresh(@RequestBody RefreshTokenPayload refreshTokenPayload){
        return BaseResponse.success(authBusinessService.refresh(refreshTokenPayload));
    }
    @PostMapping("/logout")
    public BaseResponse<Void> logout(){
        authBusinessService.logout();
        return BaseResponse.success();
    }

}
