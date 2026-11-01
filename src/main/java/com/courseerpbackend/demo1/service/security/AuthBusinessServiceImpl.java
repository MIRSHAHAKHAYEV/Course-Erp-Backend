package com.courseerpbackend.demo1.service.security;

import com.courseerpbackend.demo1.dto.response.BaseResponse;
import com.courseerpbackend.demo1.dto.response.LoginResponse;
import com.courseerpbackend.demo1.dto.response.RefreshTokenDto;
import com.courseerpbackend.demo1.dto.security.LoginPayload;
import com.courseerpbackend.demo1.dto.security.RefreshTokenPayload;
import com.courseerpbackend.demo1.dto.security.SignUpPayload;
import com.courseerpbackend.demo1.entity.role.Role;
import com.courseerpbackend.demo1.entity.user.User;
import com.courseerpbackend.demo1.exception.BaseException;
import com.courseerpbackend.demo1.mapper.UserEntityMapper;
import com.courseerpbackend.demo1.service.role.RoleServiceImpl;
import com.courseerpbackend.demo1.service.user.UserDetailsServiceImpl;
import com.courseerpbackend.demo1.service.user.UserServiceImpl;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.courseerpbackend.demo1.entity.enums.response.ErrorResponseMessages.EMAIL_ALREADY_REGISTERED;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthBusinessServiceImpl implements IAuthBusinessService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserServiceImpl userService;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleServiceImpl roleService;

    @Override
    public LoginResponse login(LoginPayload loginPayload) {
        authenticate(loginPayload);

        return prepareLoginResponse(loginPayload.getEmail(),loginPayload.isRememberMe());

    }

    @Override
    public LoginResponse refresh(RefreshTokenPayload refreshTokenPayload) {
        return prepareLoginResponse(
                refreshTokenManager.getEmail(refreshTokenPayload.getRefreshToken()),
                refreshTokenPayload.isRememberMe());
    }

    @Override
    public void logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userDetails.getUsername();
        log.info("{} user logout succeed",userDetails.getUsername());
    }

    @Override
    public void signUp(SignUpPayload payload) {
        if(userService.checkByEmail(payload.getEmail())){
            throw BaseException.of(EMAIL_ALREADY_REGISTERED);
        }
        Role defaultRole = roleService.getDefaultRole();

        User user = userEntityMapper.fromSignUpPayloadToUser(
                payload,
                passwordEncoder.encode(payload.getPassword()),
                defaultRole.getId()
                );
        userService.insert(user);
    }

    @Override
    public void setAuthentication(String email) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities())
        );
    }

    //private util methods
    private void authenticate(LoginPayload request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Exception");
        }
    }

    private LoginResponse prepareLoginResponse(String email, boolean rememberMe) {
        final User user = userService.getByEmail(email);

        return LoginResponse.builder()
                .accessToken(accessTokenManager.generate(user))
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder().user(user).isRememberMe(rememberMe).build()
                ))
                .build();
    }
}
