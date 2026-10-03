package com.courseerpbackend.demo1;

import com.courseerpbackend.demo1.entity.enums.user.UserStatus;
import com.courseerpbackend.demo1.entity.user.User;
import com.courseerpbackend.demo1.repository.UserRepository;
import com.courseerpbackend.demo1.service.base.TokenReader;
import com.courseerpbackend.demo1.service.security.AccessTokenManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

@SpringBootApplication
@AllArgsConstructor
public class Demo1Application implements CommandLineRunner {
    final AccessTokenManager accessTokenManager;
    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }
    @Override
    public void run(String... args) throws Exception {

    }
}
