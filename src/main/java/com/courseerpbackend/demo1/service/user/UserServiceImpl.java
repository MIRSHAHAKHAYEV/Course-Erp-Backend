package com.courseerpbackend.demo1.service.user;

import com.courseerpbackend.demo1.entity.user.User;
import com.courseerpbackend.demo1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService{

    final UserRepository userRepository;

    @Override
    public User getByEmail(String email) {
       return userRepository.findUserByEmailAndIsDeletedFalse(email).orElseThrow(()->new UsernameNotFoundException("user not found: "+email));
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }
}
