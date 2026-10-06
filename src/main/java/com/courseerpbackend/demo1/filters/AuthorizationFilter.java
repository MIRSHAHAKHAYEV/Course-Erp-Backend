package com.courseerpbackend.demo1.filters;

import com.courseerpbackend.demo1.constants.TokenConstants;
import com.courseerpbackend.demo1.service.security.AccessTokenManager;
import com.courseerpbackend.demo1.service.security.AuthBusinessServiceImpl;
import com.courseerpbackend.demo1.service.security.IAuthBusinessService;
import com.courseerpbackend.demo1.service.user.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.io.IOException;
import java.util.Objects;

import static com.courseerpbackend.demo1.constants.TokenConstants.EMAIL_KEY;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AccessTokenManager accessTokenManager;
    private final IAuthBusinessService authBusinessService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Objects.nonNull(token) && token.startsWith(TokenConstants.PREFIX)){
            authBusinessService.setAuthentication(
                    accessTokenManager.getEmail(
                            token.substring(7).trim()
                    )
            );
        }
        filterChain.doFilter(request,response);
    }

}
