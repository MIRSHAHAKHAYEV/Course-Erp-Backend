package com.courseerpbackend.demo1.service.user;

import com.courseerpbackend.demo1.dto.security.LoggedInUserDetails;
import com.courseerpbackend.demo1.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getByEmail(username);
        return new LoggedInUserDetails(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
