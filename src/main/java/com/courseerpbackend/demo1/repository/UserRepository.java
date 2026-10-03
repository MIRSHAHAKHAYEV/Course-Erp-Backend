package com.courseerpbackend.demo1.repository;

import com.courseerpbackend.demo1.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmailAndIsDeletedFalse(String email);
}
