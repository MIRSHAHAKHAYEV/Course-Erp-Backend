package com.courseerpbackend.demo1;

import com.courseerpbackend.demo1.entity.enums.user.UserStatus;
import com.courseerpbackend.demo1.entity.user.User;
import com.courseerpbackend.demo1.repository.UserRepository;
import com.courseerpbackend.demo1.service.base.TokenReader;
import com.courseerpbackend.demo1.service.security.AccessTokenManager;
import com.courseerpbackend.demo1.service.user.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootApplication
@AllArgsConstructor
public class Demo1Application implements CommandLineRunner {
    final AccessTokenManager accessTokenManager;
    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;
    final UserServiceImpl userService;
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
//        User user = User.builder()
//                .name("Test")
//                .surname("test")
//                .password(passwordEncoder.encode("123123"))
//                .roleId(2L)
//                .phoneNumber("123123")
//                .status(UserStatus.ACTIVE)
//                .email("mail")
//                .build();
//
//        userService.insert(user);
    }
}
